package org.example;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.example.oop.Album;
import org.example.oop.Artist;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.example.oop.Chart;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Faker faker = new Faker();
        Database.createTables();
        Database.createTableCharts();
        Artist artist1=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist2=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist3=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist4=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist5=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist6=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist7=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist8=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist9=new Artist(faker.name().firstName(),faker.country().name());
        Artist artist10=new Artist(faker.name().firstName(),faker.country().name());

        Album album1=new Album(faker.music().genre(),1,faker.number().numberBetween(1900,2020));
        Album album2=new Album(faker.music().genre(),2,faker.number().numberBetween(1900,2020));
        Album album3=new Album(faker.music().genre(),3,faker.number().numberBetween(1900,2020));
        Album album4=new Album(faker.music().genre(),4,faker.number().numberBetween(1900,2020));
        Album album5=new Album(faker.music().genre(),5,faker.number().numberBetween(1900,2020));
        Album album6=new Album(faker.music().genre(),6,faker.number().numberBetween(1900,2020));
        Album album7=new Album(faker.music().genre(),7,faker.number().numberBetween(1900,2020));
        Album album8=new Album(faker.music().genre(),8,faker.number().numberBetween(1900,2020));
        Album album9=new Album(faker.music().genre(),9,faker.number().numberBetween(1900,2020));
        Album album10=new Album(faker.music().genre(),10,faker.number().numberBetween(1900,2020));


/**
 * functia fillChartTable() preia toate informatiile din tabelele Artists si Albums si le insereaza in noua tabela
 * Chart ,albumele fiind sortate dupa id
 * functia getRankingList() preia toate numele artisitilor din tabela Chart si ii afiseaza in acea ordine =>rezultand
 * clasamentul artistilor care va fi afisat in terminal
 */
      Chart ch=new Chart();
      ch.fillChatsTable();
      ch.getRankingList();
/*
 * Cu un obict de tipul Configuration setam versiunea de FreeMarker pe care am folosit-o si a carei dependinte am adaugat-o
 * in fisierul pom.xml,de asemenea setam si clasa a carei resurse vor fi folosite pentru a
 * a incarca sabloane din interiorul pachetului specificat
 * functia getTemplate(..) preia sablonul cu numele dat
 * Cu ajutorul unui obiect de tip StringWriter , dupa ce ne cream un map ce contine numele tutor artistilor(clasamentul),
 * ne ajuta sa scriem in sablon datele stocate de map
 * De asemenea am creat un fisier de output de tip html in care cu ajutorul obictului template am adaugat informatiile continute
 * de map
 */
      Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
      configuration.setClassForTemplateLoading(App.class, "/");

        try {
            Template template = configuration.getTemplate("reportHTML.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("names", ch.getRanking());

            template.process(map, writer);

            Writer file = new FileWriter(new File("template-output.html"));
            template.process(map, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

