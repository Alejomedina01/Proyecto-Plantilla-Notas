package tests;

import models.Manager;
import models.Scale;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagerTest {

    public static final ArrayList<String> PRUEBA_ESCALAS = new ArrayList<String>(Arrays.asList("A", "A", "D", "B", "B", "A", "B", "C", "A", "A",
            "B", "B", "D", "B", "C", "C", "B", "A"));
    public static final ArrayList<Integer> PRUEBA_RESPUESTAS = new ArrayList<Integer>(Arrays.asList(2, 1, 2, 4, 2, 2, 3, 1, 2, 2, 4, 1, 1, 2, 1, 3, 3, 1));

    public static final ArrayList<Double> ESPERADO_NOTAS = new ArrayList<Double>(Arrays.asList(1.5, 3.5, 5.0, 5.0, 2.0, 1.5, 3.5, 5.0, 1.5, 1.5, 5.0, 0.0, 0.0, 2.0, 5.0, 0.0, 3.5, 3.5));
    public static final ArrayList<Double> ESPERADO_PORCENTAJES = new ArrayList<Double>(Arrays.asList(0.08, 0.18, 0.25, 0.50, 0.10, 0.05, 0.14, 0.15, 0.08, 0.08, 0.50, 0.0, 0.0, 0.04, 0.20, 0.0, 0.35, 0.35));

    public static final double NOTA_MAXIMA = 5.0;

    public static final double NOTA_PROMEDIO = 3.0;

    private void fillScales(Manager manager){
        ArrayList<Scale> scales = new ArrayList<>();

        Scale A = new Scale("A");
        A.getAnswers().add(0.7);
        A.getAnswers().add(0.3);
        scales.add(A);

        Scale B = new Scale("B");
        B.getAnswers().add(0.0);
        B.getAnswers().add(0.4);
        B.getAnswers().add(0.7);
        B.getAnswers().add(1.0);
        scales.add(B);

        Scale C = new Scale("C");
        C.getAnswers().add(1.0);
        C.getAnswers().add(0.6);
        C.getAnswers().add(0.0);
        scales.add(C);

        Scale D = new Scale("D");
        D.getAnswers().add(0.0);
        D.getAnswers().add(1.0);
        scales.add(D);
        manager.setScales(scales);
    }

    private void fillLevels(Manager manager){
        //Nivel 1
        manager.addPart(0,  "Uno", 0.3);//1
        manager.addPart(0,  "Dos", 0.4);//2
        manager.addPart(0, "Tres", 0.3);//3

        //Nivel 2
        manager.addPart(1, "Uno", 0.15);//4
        manager.addPart(1,  "Dos", 0.15);//5

        manager.addPart(2,  "Uno", 0.15);//6
        manager.addPart(2,  "Uno", 0.15);//7
        manager.addPart(2,  "Uno", 0.15);//8

        manager.addPart(3,  "Uno", 0.2);//9
        manager.addPart(3,  "Dos", 0.1);//10

        //Nivel 3
        manager.addPart(4,  "Uno", 0.05);//11
        manager.addPart(4,  "Dos", 0.05);//12
        manager.addPart(4,  "Tres", 0.05);//13

        manager.addPart(5,  "Uno", 0.1);//14
        manager.addPart(5,  "Dos", 0.05);//15

        manager.addPart(6,  "Uno", 0.03);//16
        manager.addPart(6,  "Dos", 0.04);//17
        manager.addPart(6,  "Tres", 0.03);//18

        manager.addPart(7,  "Uno", 0.05);//19
        manager.addPart(7,  "Dos", 0.05);//20

        manager.addPart(8,  "Uno", 0.1);//21
        manager.addPart(8,  "Dos", 0.05);//22
        manager.addPart(8,  "Tres", 0.05);//23

        manager.addPart(9,  "Uno", 0.02);//24
        manager.addPart(9,  "Dos", 0.04);//25
        manager.addPart(9,  "Tres", 0.04);//26

        manager.addPart(10,  "Uno", 0.1);//27
        manager.addPart(10,  "Dos", 0.1);//28
    }

    @Test
    public void testCalculateGrades() {
        Manager manager = new Manager(NOTA_MAXIMA);
        fillScales(manager);
        ArrayList<Double> grades = manager.calculateGrades(PRUEBA_ESCALAS, PRUEBA_RESPUESTAS);
        Assert.assertEquals(grades, ESPERADO_NOTAS);
    }

    @Test
    public void testCalculatePercentages(){
        Manager manager = new Manager(NOTA_MAXIMA);
        fillLevels(manager);
        manager.extractLeaves();
        ArrayList<Double> percentages = manager.calculatePercentagesGrade(ESPERADO_NOTAS);
        Assert.assertEquals(percentages, ESPERADO_PORCENTAJES);
    }

    @Test
    public void testCalculateAverageGrade(){
        Manager manager = new Manager(NOTA_MAXIMA);
        fillLevels(manager);
        manager.extractLeaves();
        ArrayList<Double> percentages = manager.calculatePercentagesGrade(ESPERADO_NOTAS);
        double average = manager.calculateAverage(percentages);
        Assert.assertEquals(average, NOTA_PROMEDIO, 0.0);
    }
}