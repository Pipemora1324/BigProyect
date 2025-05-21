// src/main/java/com/plantsim/service/AuthService.java
package com.plantsim.service;

import com.plantsim.dto.AuthResponse;
import com.plantsim.dto.LoginRequest;
import com.plantsim.dto.RegisterRequest;
import com.plantsim.model.User;
import com.plantsim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Registra un nuevo usuario
     */
    public AuthResponse register(RegisterRequest request) {
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }
        
        // Crear nuevo usuario
        User user = new User(
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()),
            request.getName()
        );
        
        // Guardar en la base de datos
        user = userRepository.save(user);
        
        // Generar token JWT
        String token = jwtService.generateToken(user.getEmail());
        
        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail());
    }
    
    /**
     * Inicia sesión de un usuario
     */
    public AuthResponse login(LoginRequest request) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        
        // Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        // Generar token JWT
        String token = jwtService.generateToken(user.getEmail());
        
        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail());
    }
}