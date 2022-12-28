package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class Control {


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable long id)throws ServletException , IOException{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Computer computer =entityManager.find(Computer.class, id);
        entityManager.remove(computer);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    return "delete";
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String update(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable long id)throws ServletException , IOException{
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Computer computer =entityManager.find(Computer.class, id);
        entityManager.close();
        entityManagerFactory.close();
        HttpSession session = request.getSession();

        String a = computer.getName();
        double c = computer.getPrice_usd();
        session.setAttribute("ids",id);
        model.addAttribute("name",a);
        model.addAttribute("price_usd",c);
        model.addAttribute("id",id);

        return "update";
    }
    @RequestMapping(value="/updateAfter", method = RequestMethod.POST)
    public String updateAfter(Model model,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        HttpSession session = request.getSession();
        entityManager.getTransaction().begin();

        Long z = Long.parseLong(session.getAttribute("ids").toString());
        Computer computer =entityManager.find(Computer.class, z);
        String a =request.getParameter("name");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = computer.getDate().format(formatter);
        if(formattedString.equals("2022-01-02")){
            computer.setDate(LocalDate.of(2022, 01, 3));
        }else{if(formattedString.equals("2022-01-09")){
            computer.setDate(LocalDate.of(2022, 01, 10));
        }else{}}
        
        double c= Double.parseDouble(request.getParameter("price_usd"));
        double d = set_PLN(c,computer.getDate());
        model.addAttribute("id",z);
        computer.setPrice_usd(c);
        computer.setPrice_pln(d);
        computer.setName(a);
        entityManager.merge(computer);
        entityManager.getTransaction().commit();

        return "updateAfter";
    }
    @RequestMapping("/addRandom")
    public String addRandom(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
        HttpSession session = request.getSession();

        String a = (String) session.getAttribute("computers");
        Computer computer = ComputerData.getRandomComputer();
        add(computer);
        model.addAttribute("name",computer.getName());
        model.addAttribute("date",computer.getDate());
        model.addAttribute("price_usd", computer.getPrice_usd());
        model.addAttribute("price_pln",computer.getPrice_pln());
        a = a + computer.toString();
        session.setAttribute("computers",a);
        return "result";
    }
    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers",Computer.class).getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping("/viewbydateasc")
    public String viewbydateasc(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY date ASC ",Computer.class).getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping("/viewbydatedesc")
    public String viewbydatedesc(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY date DESC ",Computer.class).getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping("/viewbynameasc")
    public String viewbynameasc(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY name ASC ",Computer.class).getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping("/viewbynamedesc")
    public String viewbynamedesc(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List resultList = entityManager.createNativeQuery(" SELECT * FROM Computers ORDER BY name DESC ",Computer.class).getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping(value = "/findbytext")
    public String findbytext(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("localdatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String name = request.getParameter("name").toString();
        Query query = entityManager.createNativeQuery(" SELECT * FROM Computers WHERE name LIKE :searchKeyword",Computer.class);
        query.setParameter("searchKeyword", "%"+name+"%");
        List resultList = query.getResultList();
        Computer[] array = new Computer[resultList.size()];
        for(int i = 0; i < resultList.size(); i++)
        { array[i] = (Computer) resultList.get(i); }
        request.setAttribute("computers", array);
        request.getRequestDispatcher("output.jsp").forward(request, response);
        entityManager.close();
        entityManagerFactory.close();
        return "output";
    }
    @RequestMapping(value = "/")
    public String home(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
        HttpSession session = request.getSession(true);
        String a = "";
        session.setAttribute("computers",a);
        return "home";
    }
    @RequestMapping(value = "/home")
    public String home2(Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
        HttpSession session = request.getSession();
        String a = (String) session.getAttribute("computers");
        model.addAttribute("output", a);

        return "home";
    }
    @RequestMapping(value = "/add")
    public String addCustom( HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {

        return "add";
    }
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
        HttpSession session = request.getSession();
        String a = (String) session.getAttribute("computers");
        Computer computer = new Computer();
        model.addAttribute("name", request.getParameter("name"));
        computer.setName(request.getParameter("name"));
        computer.setPrice_usd(Double.parseDouble(request.getParameter("price_usd")));
        model.addAttribute("price_usd", computer.getPrice_usd());
        if(request.getParameter("date").equals("a")){
            computer.setDate(LocalDate.of(2022, 01, 3));
        }else{
            computer.setDate(LocalDate.of(2022, 01, 10));
        }
        model.addAttribute("date",computer.getDate());
        double pln = set_PLN(computer.getPrice_usd(),computer.getDate());
        computer.setPrice_pln(Math.round((pln* 100.0) / 100.0));
        model.addAttribute("price_pln",(double)Math.round((pln* 100.0) / 100.0));
        add(computer);
         a = a + computer.toString();
        session.setAttribute("computers",a);
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
    @RequestMapping(value = "/facture")
    public String serializeToXML(Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
        HttpSession session = request.getSession();
        String a = (String) session.getAttribute("computers");
        File xmlOutput = new File("serialized.xml");
        FileWriter fileWriter = new FileWriter(xmlOutput);
        fileWriter.write("<Faktura>");
        fileWriter.write(a);
        fileWriter.write("</Faktura>");
        model.addAttribute("output", a);
        fileWriter.close();
        return "facture";
    }
}