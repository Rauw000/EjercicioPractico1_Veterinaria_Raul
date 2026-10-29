package ejercicioPractico1_veterinaria_Raul.controller;

import ejercicioPractico1_veterinaria_Raul.domain.Servicio;
import ejercicioPractico1_veterinaria_Raul.service.ServicioService;
import ejercicioPractico1_veterinaria_Raul.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoService;

    public ServicioController(ServicioService servicioService,
                              TipoServicioService tipoService) {
        this.servicioService = servicioService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String mostrarServicios(Model model) {

        model.addAttribute(
                "servicios",
                servicioService.obtenerServicios()
        );

        return "servicios/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoServicio(Model model) {

        model.addAttribute("servicio", new Servicio());

        model.addAttribute(
                "tipos",
                tipoService.obtenerTipos()
        );

        return "servicios/formulario";
    }

    @GetMapping("/editar")
    public String editarServicio(@RequestParam Integer id,
                                 Model model,
                                 RedirectAttributes redirect) {

        var servicio = servicioService.buscarServicio(id);

        if (servicio.isEmpty()) {

            redirect.addFlashAttribute(
                    "error",
                    "El servicio veterinario no existe."
            );

            return "redirect:/servicios";
        }

        model.addAttribute("servicio", servicio.get());

        model.addAttribute(
                "tipos",
                tipoService.obtenerTipos()
        );

        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(
            @ModelAttribute Servicio servicio,
            RedirectAttributes redirect) {

        boolean esNuevo = servicio.getId() == null;

        if (servicio.getTipoServicio() == null
                || servicio.getTipoServicio().getId() == null
                || tipoService.buscarTipo(
                        servicio.getTipoServicio().getId()
                ).isEmpty()) {

            redirect.addFlashAttribute(
                    "error",
                    "Debe seleccionar un tipo de servicio válido."
            );

            return esNuevo
                    ? "redirect:/servicios/nuevo"
                    : "redirect:/servicios/editar?id=" + servicio.getId();
        }

        servicioService.guardarServicio(servicio);

        redirect.addFlashAttribute(
                "mensaje",
                esNuevo
                        ? "Servicio veterinario registrado correctamente."
                        : "Servicio veterinario actualizado correctamente."
        );

        return "redirect:/servicios";
    }

    @PostMapping("/eliminar")
    public String eliminarServicio(@RequestParam Integer id,
                                   RedirectAttributes redirect) {

        var servicio = servicioService.buscarServicio(id);

        if (servicio.isEmpty()) {

            redirect.addFlashAttribute(
                    "error",
                    "El servicio veterinario no existe."
            );

            return "redirect:/servicios";
        }

        if (servicio.get().getCitas() != null
                && !servicio.get().getCitas().isEmpty()) {

            redirect.addFlashAttribute(
                    "error",
                    "No se puede eliminar el servicio porque tiene citas asociadas."
            );

            return "redirect:/servicios";
        }

        servicioService.eliminarServicio(id);

        redirect.addFlashAttribute(
                "mensaje",
                "Servicio veterinario eliminado correctamente."
        );

        return "redirect:/servicios";
    }
}