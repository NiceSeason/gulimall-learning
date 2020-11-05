package io.niceseason.gulimall.cart.service;

import io.niceseason.gulimall.cart.vo.CartItemVo;
import io.niceseason.gulimall.cart.vo.CartVo;

import java.util.List;

public interface CartService {
    CartItemVo addCartItem(Long skuId, Integer num);

    CartItemVo getCartItem(Long skuId);

    CartVo getCart();

    void checkCart(Long skuId, Integer isChecked);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItemVo> getCheckedItems();
}
