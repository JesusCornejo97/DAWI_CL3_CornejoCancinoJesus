package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Producto;
import com.example.demo.repositories.IProductoDao;
import com.example.demo.services.ProductoService;
import com.example.demo.services.ProductoServiceImpl;

import cibertec.edu.models.Student;
import cibertec.edu.services.StudentServiceImpl;
import net.sf.jasperreports.engine.JRException;



@Controller
public class MainController {
	
	@Autowired
	private IProductoDao ProductoRepository;
	@Autowired
	private ProductoServiceImpl productoService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/registro")
	public String registrarProducto(@ModelAttribute("producto") Producto producto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "registro";
		} else {
			return "acceso_denegado";
		}
	}
	
	@PostMapping("/registro")
	public String registrarProducto(@Validated @ModelAttribute("producto") Producto producto, BindingResult binding) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if(binding.hasErrors()) {
				return "registro";
			}
			
			ProductoRepository.save(producto);
			
			return "redirect:/";
			
		} else {
			return "acceso_denegado";
		}
	}
	
	@ModelAttribute("productos")
	public List<Producto> obtenerProductos(){
		List<Producto> productos = productoService.getAllProductos();		
		return productos;
	}
	
	@GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reporteProductos() throws IOException, JRException {
		try {
			InputStream report = this.productoService.getReportProductos();
			byte[] data = report.readAllBytes();
			report.close();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_PDF);
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"reporte_productos.pdf\"");
			header.setContentLength(data.length);
			
			return new ResponseEntity<byte[]>(data,header, HttpStatus.CREATED);			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("IO Error retornando archivo");
		}
	}

}
