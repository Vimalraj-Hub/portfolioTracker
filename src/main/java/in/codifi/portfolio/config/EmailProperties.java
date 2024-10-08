package in.codifi.portfolio.config;


import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@ApplicationScoped
@Getter
@Setter
public class EmailProperties {

	@ConfigProperty(name = "appconfig.mail.recipient.ids")
	private String recipientIds;
}
