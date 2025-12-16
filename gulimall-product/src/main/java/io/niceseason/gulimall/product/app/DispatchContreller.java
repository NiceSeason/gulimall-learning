package io.niceseason.gulimall.product.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dispatch/remoteDispatch")
public class DispatchContreller {




    @PostMapping("/send")
    public void dispatch() {

    }
}
