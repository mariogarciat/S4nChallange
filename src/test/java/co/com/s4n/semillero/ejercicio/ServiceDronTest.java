package co.com.s4n.semillero.ejercicio;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Dron;
import co.com.s4n.semillero.ejercicio.dominio.entidades.Ruta;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceDron;
import co.com.s4n.semillero.ejercicio.dominio.servicios.ServiceFiles;
import co.com.s4n.semillero.ejercicio.dominio.vo.Orientacion;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import io.vavr.collection.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

//@RunWith(JUnitPlatform.class)
public class ServiceDronTest {

    @Test
    public void testCrearDron(){
        //Dron mockedDron = mock(Dron.class);
        Dron dron = new Dron();
        assertEquals(0,dron.getX());
        assertEquals(0,dron.getY());
        assertEquals(Orientacion.norte,dron.getD());
    }

    @Test
    public void testAvanzar(){
        Dron d = new Dron();
        Dron dronTest = new Dron(-2,4,Orientacion.norte);
        Future<Dron> dron = ServiceDron.mover("AAAAIAAD", d);


        assertEquals(dronTest.getY(), dron.get().getY());
        assertEquals(dronTest.getX(), dron.get().getX());
        assertEquals(dronTest.getD(), dron.get().getD());

    }

    @Test
    public void testLeerArchivo(){
        String ruta = "./src/test/resources/in.txt";
        Stream<String> streamTeat = Stream.of("AAAAIAAD","DDAIAD", "AAIADAD");
        Try<Stream<String>> streams = ServiceFiles.leerArchivo(ruta);

        Option<java.util.List<String>> list = ServiceFiles.craerLista(streams);

        assertEquals("AAIADAD",list.getOrElse(new ArrayList<>()).get(2));


    }

    /*@Test
    public void testListToPedidos(){
        String[] array = {"AAAAIAAD","DDAIAD","AAIADAD","ADAI","AAIAD","IIADAD"
                ,"AAAAIAAD","DDAIAD","AAIADAD","AAAAIAAD", "DDAIAD", "AAIADAD", "HOLA"};
        java.util.List<String> list = Arrays.asList(array);
        io.vavr.collection.List<io.vavr.collection.List<String>> lists = ServiceFiles.listaAPedidos(list);

        System.out.println("tamaño de la lista "+ lists.get(3).size());
        System.out.println("Primer elemento de las listas"+lists.get(0).get(0));
        assertEquals("DDAIAD",lists.get(3).get(1));
        assertEquals("HOLA", lists.get(4).get(0));
    }*/

    @Test
    public void testPedidos(){
        String ruta = "./src/test/resources/in.txt";
        Try<Stream<String>> streams = ServiceFiles.leerArchivo(ruta);
        Option<java.util.List<String>> listaCreada = ServiceFiles.craerLista(streams);

        //System.out.println(list);
        io.vavr.collection.List<io.vavr.collection.List<String>> listaF = ServiceFiles.listaAPedidos(listaCreada.get());

        System.out.println(listaF);

        List<Ruta> rutas = ServiceFiles.listARuta(listaF);
        Dron dron = new Dron();
        List<Dron> drons = rutas.flatMap(r -> ServiceDron.cargarRuta(r));
        Dron fdron = drons.reverse().head();
        Dron dronTest = new Dron(-3,1,Orientacion.oeste);

        System.out.println("final: "+fdron.toString());
        System.out.println("test: "+dronTest.toString());
        assertEquals(dronTest.getY(), fdron.getY());
        assertEquals(dronTest.getX(), fdron.getX());
        assertEquals(dronTest.getD(), fdron.getD());
    }

    @Test
    public void testCargarRuta(){

        Dron dronTest = new Dron(0,0,Orientacion.oeste);

        Dron dron = new Dron();
        List<String> of = List.of("AAAAIAAD", "DDAIAD", "AAIADAD");
        Ruta ruta1 = new Ruta(of);

        /*Future<Dron> fdron = ServiceDron.cargarRuta(dron, ruta1);

        System.out.println(fdron.get().toString());
        assertEquals(0,fdron.get().getX());

        assertEquals(dronTest.getY(), fdron.get().getY());
        assertEquals(dronTest.getX(), fdron.get().getX());
        assertEquals(dronTest.getD(), fdron.get().getD());*/
    }












}
