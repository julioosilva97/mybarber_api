package com.mybarber.api.api.controller;/*
 * package com.mybarber.api.api.controller;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.ui.Model; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.servlet.mvc.support.RedirectAttributes;
 * 
 * import com.mybarber.api.api.model.PasswordResetDto; import
 * com.mybarber.api.domain.entity.PasswordResetToken; import
 * com.mybarber.api.domain.entity.Usuario; import
 * com.mybarber.api.domain.repository.TokenDeVerificadaoDAO; import
 * com.mybarber.api.domain.service.UsuarioService;
 * 
 * @Controller
 * 
 * @RequestMapping("/reset-password") public class PasswordResetController {
 * 
 * @Autowired private UsuarioService userService;
 * 
 * @Autowired private TokenDeVerificadaoDAO tokenRepository;
 * 
 * @Autowired private BCryptPasswordEncoder encoder;
 * 
 * @ModelAttribute ("passwordResetForm") public PasswordResetDto passwordReset()
 * { return new PasswordResetDto(); }
 * 
 * @GetMapping public String displayResetPasswordPage(@RequestParam(required =
 * false) String token, Model model) {
 * 
 * PasswordResetToken resetToken = tokenRepository.buscarPorToken(token); if
 * (resetToken == null){ model.addAttribute("error",
 * "Could not find password reset token.");
 * 
 * } else { model.addAttribute("token", resetToken.getToken()); }
 * 
 * return "senha/reset-password"; }
 * 
 * @PostMapping
 * 
 * @Transactional public String
 * handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid
 * PasswordResetDto form, BindingResult result, RedirectAttributes
 * redirectAttributes) {
 * 
 * if (result.hasErrors()){
 * redirectAttributes.addFlashAttribute(BindingResult.class.getName() +
 * ".passwordResetForm", result);
 * redirectAttributes.addFlashAttribute("passwordResetForm", form); return
 * "redirect:/reset-password?token=" + form.getToken(); }
 * 
 * PasswordResetToken token = tokenRepository.buscarPorToken(form.getToken());
 * Usuario usuario = token.getUsuario(); String updatedPassword =
 * form.getPassword(); String senhaCriptografada =
 * encoder.encode(updatedPassword); usuario.setSenha(senhaCriptografada);
 * userService.alterarSenha(usuario); tokenRepository.delete(token);
 * 
 * return "redirect:/login?resetSuccess"; }
 * 
 * }
 */