package viva;
import java.util.Scanner;

public class viva1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Statistics for the final report
        int parcelsProcessed = 0;
        double totalShippingFees = 0.0;
        int heaviestChargeableWeight = 0;

        // Keep processing parcels until the user enters EXIT
        while (true) {
            System.out.print(
                    "Enter destination postcode(or EXIT to stop): "
            );
            // Remove whitespace from the beginning and end of the input
            String postcode = sc.nextLine().trim();

            // EXIT is case-insensitive
            if (postcode.equalsIgnoreCase("EXIT")) {
                break;
            }
            // Step 1: Normalize the postcode
            postcode = postcode.replace(" ", "");
            postcode = postcode.replace("-", "");
            postcode = postcode.toUpperCase();

            // Remove the leading MY prefix
            if (postcode.startsWith("MY")) {
                postcode = postcode.substring(2);
            }

            String zone;
            double firstKgRate = 0.0;
            double additionalKgRate = 0.0;
            boolean isInternational = false;

            // Step 2: Check whether the postcode is international
            if (postcode.length() >= 2
                    && Character.isLetter(postcode.charAt(0))
                    && Character.isLetter(postcode.charAt(1))) {

                isInternational = true;
                zone = "International";
            } else {
                // Step 3: Validate Malaysian postcode
                if (!postcode.matches("\\d{5}")) {
                    System.out.println(
                            "Error: Invalid postcode. Parcel skipped."
                    );
                    continue;
                }
                int area = Integer.parseInt(
                        postcode.substring(0, 2)
                );
                if (area < 1 || area > 98) {
                    System.out.println(
                            "Error: Invalid postcode. Parcel skipped."
                    );
                    continue;
                }
                // Step 4: Classify the Malaysian destination zone
                if (area >= 50 && area <= 60) {
                    zone = "Klang Valley";
                    firstKgRate = 6.00;
                    additionalKgRate = 1.50;
                } else if ((area >= 1 && area <= 49)
                        || (area >= 61 && area <= 86)) {
                    zone = "Peninsular";
                    firstKgRate = 8.00;
                    additionalKgRate = 2.00;

                } else {
                    zone = "East Malaysia";
                    firstKgRate = 14.00;
                    additionalKgRate = 4.50;
                }
            }
            // Step 5: Read and validate parcel weight
            int grams;

            while (true) {
                System.out.print("Enter parcel weight (g): ");

                if (!sc.hasNextInt()) {
                    System.out.println(
                            "Error: Weight must be an integer."
                    );
                    sc.nextLine();
                    continue;
                }
                grams = sc.nextInt();
                sc.nextLine();

                if (grams > 0) {
                    break;
                }

                System.out.println(
                        "Error: Weight must be greater than 0."
                );

            }
            // Convert grams to kilograms
            double actualKg = grams / 1000.0;

            // Step 6: Read and validate parcel dimensions
            int length;
            int width;
            int height;

            while (true) {
                System.out.print(
                        "Enter dimensions LxWxH (cm): "
                );

                String dimensions = sc.nextLine()
                        .trim()
                        .toUpperCase();

                // Replace lowercase x by X and split into 3 parts
                String[] parts = dimensions.split("X");

                if (parts.length == 3) {
                    try {
                        length = Integer.parseInt(parts[0]);
                        width = Integer.parseInt(parts[1]);
                        height = Integer.parseInt(parts[2]);
                        if (length > 0
                                && width > 0
                                && height > 0) {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        // Invalid numbers are handled below
                    }
                }
                System.out.println(
                        "Error: Use the format LxWxH with positive "
                                + "numbers, e.g. 30x20x10."
                );
            }
            // Step 7: Calculate volumetric weight
            double volumetricKg =
                    (length * width * height) / 5000.0;

            // Step 8: Chargeable weight is the larger value,
            // rounded up to the next whole kilogram
            double largerWeight =
                    Math.max(actualKg, volumetricKg);

            int chargeableWeight =
                    (int) Math.ceil(largerWeight);

            // Step 9: Calculate the basic shipping fee
            double fee;

            if (isInternational) {
                // International parcels cost RM35 per kilogram
                fee = chargeableWeight * 35.00;

            } else {
                // Domestic parcels use first-kg and
                // additional-kg rates
                fee = firstKgRate
                        + (chargeableWeight - 1)
                        * additionalKgRate;
            }

            // Step 10: Apply discount or surcharge
            String adjustment = "None";
            if (chargeableWeight >= 5
                    && chargeableWeight <= 10) {

                fee = fee * 0.95;
                adjustment = "5% bulk discount";

            } else if (chargeableWeight > 10) {

                fee = fee * 1.15;
                adjustment = "15% heavy parcel surcharge";
            }

            // Step 11: Display the shipping summary
            System.out.println();
            System.out.println("Shipping Summary");
            System.out.println("Postcode: " + postcode);
            System.out.println("Zone: " + zone);

            System.out.printf(
                    "Actual Weight: %.2f kg | "
                            + "Volumetric Weight: %.2f kg%n",
                    actualKg,
                    volumetricKg
            );
            System.out.println(
                    "Chargeable Weight: "
                            + chargeableWeight + " kg"
            );
            System.out.println(
                    "Adjustment: " + adjustment
            );
            System.out.printf(
                    "Shipping Fee: RM %.2f%n",
                    fee
            );

            // Step 12: Update final statistics
            parcelsProcessed++;
            totalShippingFees += fee;

            // Store only the largest chargeable weight
            if (chargeableWeight
                    > heaviestChargeableWeight) {
                heaviestChargeableWeight =
                        chargeableWeight;
            }
            System.out.println();
        }

        // Step 13: Display the final report
        System.out.println(
                "Parcels Processed: " + parcelsProcessed
        );

        System.out.printf(
                "Total Shipping Fees: RM %.2f%n",
                totalShippingFees
        );
        System.out.println(
                "Heaviest Chargeable Weight: "
                        + heaviestChargeableWeight + " kg"
        );

        sc.close();
    }
}


