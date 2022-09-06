package com.example.clase3gtics.controller;

import com.example.clase3gtics.entity.Shipper;
import com.example.clase3gtics.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
// frank puta
//commit master
@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    ShipperRepository shipperRepository;

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Shipper shipper) {
        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrm";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/editF1")
    public String editarTransportistaF1(Model model,
                                        @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrmF1";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @PostMapping("/saveF1")
    public String actualizarNuevoTransportistaF1(Shipper shipper) {

        Optional<Shipper> opt = shipperRepository.findById(shipper.getShipperId());

        if (opt.isPresent()) {
            Shipper shipperOriginal = opt.get();
            shipperOriginal.setCompanyname(shipper.getCompanyname());
            shipperRepository.save(shipperOriginal);
        }
        return "redirect:/shipper/list";
    }

    @GetMapping("/editF2")
    public String editarTransportistaF2(Model model,
                                        @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrmF2";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @PostMapping("/saveF2")
    public String actualizarNuevoTransportistaF2(Shipper shipper) {

        Optional<Shipper> opt = shipperRepository.findById(shipper.getShipperId());

        if (opt.isPresent()) {
            shipperRepository.actualizarTelefonoShipper(shipper.getPhone(), shipper.getShipperId());
        }
        return "redirect:/shipper/list";
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipperRepository.deleteById(id);
        }
        return "redirect:/shipper/list";

    }

    @PostMapping("/BuscarTransportistas")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        //List<Shipper> lista = shipperRepository.findByCompanyName(searchField);
        List<Shipper> lista = shipperRepository.buscarPorNombreParcial(searchField);
        model.addAttribute("shipperList", lista);
        model.addAttribute("searchField", searchField);

        return "shipper/list";
    }


}

