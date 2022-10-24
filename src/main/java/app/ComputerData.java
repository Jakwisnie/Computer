package app;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class ComputerData {
    private static final DecimalFormat dfSharp = new DecimalFormat("#.##");
    private static String[] names = {"Apple", "Dell", "HP", "Lenovo", "Acer", "Asus","Toshiba","Gigabyte",
            "Samsung","MSI","Sony"};

    public static Computer getRandomComputer() throws IOException {
        Computer computer = new Computer();
        double usd = ((Math.random()*901)+100);
        double usdFixed =Math.round((usd* 100.0) / 100.0);
        computer.setName(names[(int) (Math.random() * names.length)]);
        computer.setPrice_usd(usdFixed);
        computer.setDate(LocalDate.of(2022, 01, 11));
        double pln = Control.set_PLN(usdFixed, computer.getDate()) ;
        double plnFixed =Math.round((pln* 100.0) / 100.0);
        computer.setPrice_pln(plnFixed);

        return computer;

    }
}
