package mizael.jackeline.api.security;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlocklistService {

    private final Set<String> tokensRevogados = ConcurrentHashMap.newKeySet();

    public void revogar(String token) {
        tokensRevogados.add(token);
    }

    public boolean estaRevogado(String token) {
        return tokensRevogados.contains(token);
    }
}