package app;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="COMPUTERS")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;
    private double price_usd;
    private double price_pln;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public double getPrice_pln() {
        return price_pln;
    }

    public void setPrice_pln(double price_pln) {
        this.price_pln = price_pln;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "<komputer> " +
                "<nazwa> " + name +" </nazwa>"+
                "<data_ksiegowania> " + date +" </data_ksiegowania>"+
                "<koszt_USD> " + price_usd + " </koszt_USD>"+
                "<koszt_PLN> "+price_pln +" </koszt_PLN>"+
                " </komputer>";
    }
}
