package com.keycloakdemo.authserver.keycloak.app;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Long> {

}