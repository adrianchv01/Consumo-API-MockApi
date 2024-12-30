package org.api.apimockapi.service;

import lombok.RequiredArgsConstructor;
import org.api.apimockapi.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    //Nos saldra un error porque el restTemplate aun no esta incializado en el contexto
    //Para esto crearemos RestTemplateConfig
    private final RestTemplate restTemplate;

    //Definimos la url en apllication propertir y la traemos
    @Value("${spring.external.service.base-url}")
    private String baseUrl;

    public List<UserDTO> getAllUsers() {
        //La respuesta del restTemplate lo guardamos en response
        UserDTO[] response = restTemplate.getForObject(baseUrl + "/users", UserDTO[].class);

        //Vamos a convertir el array en una lista
        return Arrays.asList(response);
    }

    public void saveUser(UserDTO userDTO) {
        restTemplate.postForObject(baseUrl + "/users", userDTO, UserDTO.class);

    }

    public void updateUser(Integer id, UserDTO userDTO) {

        restTemplate.put(baseUrl + "/users/" + id, userDTO);

    }
    public void deleteUser(Integer id) {
        restTemplate.delete(baseUrl + "/users/" + id);
    }

}
