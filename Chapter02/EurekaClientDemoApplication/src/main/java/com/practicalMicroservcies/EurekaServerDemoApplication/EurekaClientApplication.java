package com.practicalMicroservcies.EurekaServerDemoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
    @RestController
    class ServiceInstanceRestController {
        @Autowired
        private DiscoveryClient discoveryClient;
        @RequestMapping("/Demo/{UserName}")
        public  String serviceInstancesByApplicationName(
                @PathVariable String UserName) {
            return "Hello "+UserName;
} }
}
