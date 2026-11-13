/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author aditi
 */
import java.util.Scanner;
public class FOPViva01Q04{

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args){
        System.out.println("Name:ADITI GHOSH");
        System.out.println("Student ID:25089207");
        Scanner scanner = new Scanner(System.in);
      // Hub initial state and tracking variables
        double hubBattery = 150.00;
        int vehiclesCharged = 0;
        double totalEnergyDelivered = 0.0;
        double totalRevenue = 0.0;

        // Vehicle specifications
        String[] names = {"Compact Hatch", "Family SUV", "Delivery Van", "E-Scooter"};
        double[] capacities = {40.0, 75.0, 90.0, 4.0};
        int[] maxPowers = {50, 120, 80, 3};
        boolean[] isCharged = {false, false, false, false};

        // Main loop runs while the hub has energy
        while (hubBattery > 0.0001) {
            System.out.println("===== Solar EV Charging Hub =====");
            System.out.printf("Hub Battery: %.2f kWh\n", hubBattery);

            for (int i = 0; i < 4; i++) {
                String doneTag = isCharged[i] ? " [DONE]" : "";
                System.out.printf("%d. %s (%.0f kWh, max %d kW)%s\n", 
                        (i + 1), names[i], capacities[i], maxPowers[i], doneTag);
            }
            System.out.println("0. Close hub");

            // Vehicle selection and validation
            int choice = -1;
            while (true) {
                System.out.print("Select a vehicle (0-4): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Error: Please enter an integer from 0 to 4.");
                    scanner.next(); // Clear invalid input
                    continue;
                }
                choice = scanner.nextInt();

                if (choice < 0 || choice > 4) {
                    System.out.println("Error: Invalid vehicle selection.");
                } else if (choice != 0 && isCharged[choice - 1]) {
                    System.out.println("Error: This vehicle has already been charged.");
                } else {
                    break;
                }
            }

            // Option 0 closes the hub
            if (choice == 0) {
                System.out.println("Hub closed by operator.");
                break;
            }

            int idx = choice - 1;

            // Current battery level validation (0-99)
            int currentLevel = -1;
            while (true) {
                System.out.print("Enter current battery level (0-99): ");
                if (scanner.hasNextInt()) {
                    currentLevel = scanner.nextInt();
                    if (currentLevel >= 0 && currentLevel <= 99) {
                        break;
                    }
                } else {
                    scanner.next();
                }
                System.out.println("Error: Current level must be between 0 and 99.");
            }

            // Target battery level validation (currentLevel + 1 to 100)
            int targetLevel = -1;
            while (true) {
                System.out.printf("Enter target battery level (%d-100): ", (currentLevel + 1));
                if (scanner.hasNextInt()) {
                    targetLevel = scanner.nextInt();
                    if (targetLevel > currentLevel && targetLevel <= 100) {
                        break;
                    }
                } else {
                    scanner.next();
                }
                System.out.println("Error: Target must be higher than the current level and at most 100.");
            }

            // Energy and power calculations
            double capacity = capacities[idx];
            int chargingPower = Math.min(maxPowers[idx], 60);
            double delivered = capacity * (targetLevel - currentLevel) / 100.0;
            double drawn = delivered / 0.90;

            boolean depleted = false;
            double reachedPercent = targetLevel;

            // Check for partial charge if hub battery is insufficient
            if (drawn > hubBattery) {
                drawn = hubBattery;
                delivered = hubBattery * 0.90;
                reachedPercent = currentLevel + (delivered / capacity) * 100.0;
                hubBattery = 0.0;
                depleted = true;
            } else {
                hubBattery -= drawn;
            }

            double chargingTime = (delivered / chargingPower) * 60.0;

            // Cost calculation (RM 1.20/kWh with 5% discount if > 30 kWh delivered)
            double cost = delivered * 1.20;
            if (delivered > 30.0) {
                cost *= 0.95;
            }

            // Update hub totals and vehicle status
            isCharged[idx] = true;
            vehiclesCharged++;
            totalEnergyDelivered += delivered;
            totalRevenue += cost;

            // Display Charging Summary
            System.out.println("Charging Summary");
            System.out.println("Vehicle: " + names[idx]);
            System.out.printf("Battery: %d%% -> %d%%\n", currentLevel, targetLevel);
            System.out.printf("Charging Power: %d kW\n", chargingPower);
            System.out.printf("Energy Delivered: %.2f kWh (drawn from hub: %.2f kWh)\n", delivered, drawn);
            System.out.printf("Charging Time: %.2f minutes\n", chargingTime);
            System.out.printf("Cost: RM %.2f\n", cost);
            System.out.printf("Remaining Hub Battery: %.2f kWh\n", hubBattery);

            // Handle depletion event
            if (depleted) {
                System.out.printf("Hub Battery Depleted! Vehicle only reached %.2f%%.\n", reachedPercent);
                break;
            }
        }

        // Final Hub Report
        System.out.println("===== Hub Report =====");
        System.out.println("Vehicles Charged: " + vehiclesCharged);
        System.out.printf("Total Energy Delivered: %.2f kWh\n", totalEnergyDelivered);
        System.out.printf("Total Revenue: RM %.2f\n", totalRevenue);
        System.out.printf("Hub Battery Left: %.2f kWh\n", hubBattery);

        scanner.close();
    }
}
    
    

