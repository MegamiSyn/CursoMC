package jp.syned.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import jp.syned.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
