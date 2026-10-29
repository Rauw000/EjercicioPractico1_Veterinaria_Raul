package ejercicioPractico1_veterinaria_Raul.controller;

import ejercicioPractico1_veterinaria_Raul.domain.TipoServicio;
import ejercicioPractico1_veterinaria_Raul.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoService;

    public TipoServicioController(TipoServicioService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public String mostrarTipos(Model model) {

        model.addAttribute("tipos", tipoService.obtenerTipos());

        return "tiposServicio/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoTipo(Model model) {

        model.addAttribute("tipoServicio", new TipoServicio());

        return "tiposServicio/formulario";
    }

    @GetMapping("/editar")
    public String editarTipo(@RequestParam Integer id,
            Model model,
            RedirectAttributes redirect) {

        var tipo = tipoService.buscarTipo(id);

        if (tipo.isEmpty()) {
            redirect.addFlashAttribute(
                    "error", "El tipo de servicio no existe."
            );

            return "redirect:/tipos";
        }

        model.addAttribute("tipoServicio", tipo.get());

        return "tiposServicio/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipo(TipoServicio tipoServicio,
            RedirectAttributes redirect) {

        boolean esNuevo = tipoServicio.getId() == null;

        tipoService.guardarTipo(tipoServicio);

        redirect.addFlashAttribute(
                "mensaje",
                esNuevo
                        ? "Tipo de servicio registrado correctamente."
                        : "Tipo de servicio actualizado correctamente."
        );

        return "redirect:/tipos";
    }

    @PostMapping("/eliminar")
    public String eliminarTipo(@RequestParam Integer id,
            RedirectAttributes redirect) {

        var tipo = tipoService.buscarTipo(id);

        if (tipo.isEmpty()) {
            redirect.addFlashAttribute(
                    "error", "El tipo de servicio no existe."
            );

            return "redirect:/tipos";
        }

        if (tipo.get().getServicios() != null
                && !tipo.get().getServicios().isEmpty()) {

            redirect.addFlashAttribute(
                    "error",
                    "No se puede eliminar el tipo porque tiene servicios asociados."
            );

            return "redirect:/tipos";
        }

        tipoService.eliminarTipo(id);

        redirect.addFlashAttribute(
                "mensaje",
                "Tipo de servicio eliminado correctamente."
        );

        return "redirect:/tipos";
    }
}
