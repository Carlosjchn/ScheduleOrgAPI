package com.jpa1prueba.existdbmodule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jpa1prueba.existdbmodule.model.UserXmlModel;
import com.jpa1prueba.existdbmodule.repository.XmlDbRepository;
import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserXmlService {
    
    private final XmlDbRepository xmlDbRepository;
    private final UsuarioService usuarioService;
    private final XmlMapper xmlMapper;


    public UserXmlService(XmlDbRepository xmlDbRepository, UsuarioService usuarioService, ObjectMapper objectMapper) {
        this.xmlDbRepository = xmlDbRepository;
        this.usuarioService = usuarioService;
        this.xmlMapper = new XmlMapper();
    }

    public void saveUserToXml(Long userId) throws Exception {
        UserDetailDTO userDTO = usuarioService.getUsuarioDTOById(userId);
        UserXmlModel userModel = UserXmlModel.fromUserDetailDTO(userDTO);
        String userXml = xmlMapper.writeValueAsString(userModel);
        xmlDbRepository.saveDocument("user_" + userId + ".xml", userXml);
    }
    
    public void saveAllUsersToXml() throws Exception {
        List<UserDetailDTO> allUsers = usuarioService.getAll();
        for (UserDetailDTO userDTO : allUsers) {
            UserXmlModel userModel = UserXmlModel.fromUserDetailDTO(userDTO);
            String userXml = xmlMapper.writeValueAsString(userModel);
            xmlDbRepository.saveDocument("user_" + userDTO.getIdUsuario() + ".xml", userXml);
        }
    }

    public UserDetailDTO getUserFromXml(Long userId) throws Exception {
        String userXml = xmlDbRepository.getDocument("user_" + userId + ".xml");
        if (userXml != null) {
            UserXmlModel userModel = xmlMapper.readValue(userXml, UserXmlModel.class);
            // Convertir de vuelta a UserDetailDTO si es necesario
            return usuarioService.getUsuarioDTOById(userId); // Por ahora, simplemente obtener de la BD
        }
        return null;
    }

    public Map<String, String> getAllStoredUsers() throws Exception {
        return xmlDbRepository.getAllDocuments();
    }

    public void clearAllStoredUsers() throws Exception {
        xmlDbRepository.clearCollection();
    }
    
    // Nuevo método para buscar usuarios por nombre usando XPath
    public Map<String, String> searchUsersByName(String name) throws Exception {
        String xpathQuery = "//user[contains(nombre, '" + name + "')]";
        return xmlDbRepository.executeXPathQuery(xpathQuery);
    }
    
    // Nuevo método para buscar usuarios por email usando XPath
    public Map<String, String> searchUsersByEmail(String email) throws Exception {
        String xpathQuery = "//user[contains(email, '" + email + "')]";
        return xmlDbRepository.executeXPathQuery(xpathQuery);
    }
}