package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class Control {

    @RequestMapping("/addRandom")
    public String addRandom(Model model) throws IOException {
        Computer computer = ComputerData.getRandomComputer();
        add(computer);
        serializeToXML(computer);
        model.addAttribute("output", computer.toString());
        return "output";
    }
    @RequestMapping("/view")
    public String view(Model model) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers",Computer.class).getResultList();
        System.out.println(resultList.toString());
        model.addAttribute("output",resultList.toString());
        return "output";
    }
    @RequestMapping("/viewbydateasc")
    public String viewbydateasc(Model model) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY date  ASC ",Computer.class).getResultList();
        System.out.println(resultList.toString());
        model.addAttribute("output",resultList.toString());
        return "output";
    }
    @RequestMapping("/viewbydatedesc")
    public String viewbydatedesc(Model model) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY date  DESC ",Computer.class).getResultList();
        System.out.println(resultList.toString());
        model.addAttribute("output",resultList.toString());
        return "output";
    }


    @RequestMapping("/viewbynameasc")
    public String viewbynameasc(Model model) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY name  ASC ",Computer.class).getResultList();
        System.out.println(resultList.toString());
        model.addAttribute("output",resultList.toString());
        return "output";
    }
    @RequestMapping("/viewbynamedesc")
    public String viewbynamedesc(Model model) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY name  DESC ",Computer.class).getResultList();
        System.out.println(resultList.toString());
        model.addAttribute("output",resultList.toString());
        return "output";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(@ModelAttribute Computer computer) {

        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }


    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(@ModelAttribute("Computer") Computer computer, Model model) throws IOException {
        System.out.println("User Page Requested");
        model.addAttribute("name", computer.getName());
        model.addAttribute("date", LocalDate.of(2022, 01, 11));
        computer.setDate(LocalDate.of(2022, 01, 11));
        model.addAttribute("price_usd", computer.getPrice_usd());
        double pln = set_PLN(computer.getPrice_usd(),LocalDate.of(2022, 01, 11));
        computer.setPrice_pln(Math.round((pln* 100.0) / 100.0));
        model.addAttribute("price_pln",(double)Math.round((pln* 100.0) / 100.0));
        add(computer);
        serializeToXML(computer);
        return "result";
    }



    public void add(Computer computer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(computer);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }

    public static double set_PLN(double price_usd, LocalDate date) throws IOException {
        double price_pln;
        URL url = new URL ("http://api.nbp.pl/api/exchangerates/rates/c/usd/"+date+"/?format=json");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.readTree(url).findValue("ask").doubleValue());
        double trend = mapper.readTree(url).findValue("ask").doubleValue();
        price_pln = price_usd * trend;

        return price_pln;
    }
    public static void serializeToXML(Computer computer) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xmlMapper.writeValue(byteArrayOutputStream, computer);
        System.out.println(byteArrayOutputStream);
        File xmlOutput = new File("serialized.xml");
        FileWriter fileWriter = new FileWriter(xmlOutput);
        fileWriter.write("<Faktura>");
        fileWriter.write(String.valueOf(byteArrayOutputStream));
        fileWriter.write("</Faktura>");
        fileWriter.close();
    }
}