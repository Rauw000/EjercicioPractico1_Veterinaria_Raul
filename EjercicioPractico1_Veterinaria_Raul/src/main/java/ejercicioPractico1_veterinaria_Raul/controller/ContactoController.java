package ejercicioPractico1_veterinaria_Raul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping
    public String mostrarContacto() {

        return "contacto/formulario";
    }

    @PostMapping("/enviar")
    public String enviarMensaje(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String mascota,
            @RequestParam String mensaje,
            RedirectAttributes redirect) {

        redirect.addFlashAttribute(
                "mensaje",
                "¡Gracias, " + nombre
                + "! Hemos recibido tu consulta correctamente."
        );

        return "redirect:/contacto";
    }
}
