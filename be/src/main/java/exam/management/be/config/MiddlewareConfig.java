package exam.management.be.config;

import exam.management.be.model.Users;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MiddlewareConfig implements AuditorAware<Long> {
    @Override
    public @NotNull Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        if (authentication.getPrincipal() == "anonymousUser") {
            return Optional.of(0L);
        }
        int id = ((Users) authentication.getPrincipal()).getId();
        return Optional.of(Long.parseLong(String.valueOf(id)));
    }
}
