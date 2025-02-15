package com.highthon.domain.product.application;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.funding.persistence.FundingRepository;
import com.highthon.domain.product.persistence.Product;
import com.highthon.domain.product.persistence.ProductRepository;
import com.highthon.domain.support.persistence.Support;
import com.highthon.domain.support.persistence.SupportRepository;
import com.highthon.domain.user.persistence.User;
import com.highthon.domain.user.persistence.UserRepository;
import com.highthon.global.error.GlobalException;
import com.highthon.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final FundingRepository fundingRepository;
    private final SupportRepository supportRepository;
    private final UserUtil userUtil;

    @Override
    @Transactional
    public void buy(Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId)
                .orElseThrow(() -> new GlobalException("not found funding", HttpStatus.NOT_FOUND));
        Product product = funding.getProduct();

        User currentUser = userUtil.getCurrentUser();

        Support support = Support.builder()
                .product(product)
                .user(currentUser)
                .build();
        supportRepository.save(support);

        funding.addFundingAmount(product.getPrice());
        fundingRepository.save(funding);
    }

}
