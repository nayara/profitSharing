package Input;

import company.Company;

import employee.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private double profitMargin;
    private Integer employeeQuantity;
    private String employeeRole;
    private Integer annualEmployeePerformance;
    private InputValidation inputValidation = new InputValidation();
    private final String TRAINEE = "Trainee";
    private final String ANALISTIC = "Analistic";
    private final String MANAGER = "Manager";

    public Input(Integer annualEmployeePerformance, String employeeRole) {
        this.annualEmployeePerformance = annualEmployeePerformance;
        this.employeeRole = employeeRole;
    }

    public Input() {
    }

    public void readCompanyInformation() {
        try {
            readValidProfitMargin();
            readValidEmployeeQuantity();
        } catch (Exception InputMismatchException) {
            System.out.println("Input inválido.");
        }
    }

    private void readValidProfitMargin() throws InputMismatchException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Digite o valor da margem de lucro total da empresa:");
        Scanner inputUserProfitMargin = new Scanner(System.in);
        this.profitMargin = inputValidation.retrieveValidDouble(inputUserProfitMargin, readValidProfitMarginMethod());
    }

    private void readValidEmployeeQuantity() throws InputMismatchException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Digite a quantidade de funcionários da empresa:");
        Scanner inputUserWorkerQuantity = new Scanner(System.in);
        this.employeeQuantity = inputValidation.retrieveValidInteger(inputUserWorkerQuantity, readValidEmployeeQuantityMethod());
    }

    public void readEmployeeInformation() {
        readRole();
        readAnnualEmployeePerformance();
    }

    private void readRole() {
        System.out.println("Digite o cargo do funcionário que deseja consultar:");
        Scanner inputUserEmployeeRole = new Scanner(System.in);
        if (inputUserEmployeeRole.hasNext()) {
            retrieveValidRole(inputUserEmployeeRole);
        }
    }

    private void readAnnualEmployeePerformance() {
        System.out.println("Digite a performance anual do funcionário: ");
        Scanner inputUserAnnualEmployeePerformance = new Scanner(System.in);
        if (inputUserAnnualEmployeePerformance.hasNextInt()) {
            retrieveValidAnnualEmployeePerformance(inputUserAnnualEmployeePerformance);
        }
    }

    private void retrieveValidRole(Scanner inputUserEmployeeRole) {
        String role = inputUserEmployeeRole.next();
        if (TRAINEE.equalsIgnoreCase(role) || ANALISTIC.equalsIgnoreCase(role) || MANAGER.equalsIgnoreCase(role)) {
            this.employeeRole = role;
        } else {
            System.out.println("Cargo do funcionário inválido.");
            readRole();
        }
    }

    private void retrieveValidAnnualEmployeePerformance(Scanner inputUserAnnualEmployeePerformance) {
        Integer performance = inputUserAnnualEmployeePerformance.nextInt();
        if (performance >= 1 && performance <= 5) {
            this.annualEmployeePerformance = performance;
        } else {
            System.out.println("Performance anual do funcionário inválida.");
            readAnnualEmployeePerformance();
        }
    }

    private Method readValidProfitMarginMethod() throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod("readValidProfitMargin", new Class[0]);
    }

    private Method readValidEmployeeQuantityMethod() throws NoSuchMethodException {
        return this.getClass().getDeclaredMethod("readValidEmployeeQuantity", new Class[0]);
    }

    public Integer getEmployeeQuantity() {
        return employeeQuantity;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public Integer getEmployeePerformance() {
        return annualEmployeePerformance;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }
}
