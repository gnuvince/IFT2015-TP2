/*
 * Vincent Foley-Bourgon (FOLV08078309)
 * Stella Domingo (DOMC03588902)
 */


package plante;

import java.util.ArrayList;
import java.util.HashMap;

public class CmdLineArguments {
    public HashMap<String, Double> options = new HashMap<String, Double>();
    public ArrayList<String> arguments = new ArrayList<String>();

    public static void usage() {
        System.err.println("Utilisation: plante.Dessin [<options>] <n> <omega> [<regles>]");
        System.err.println("Options:");
        System.err.println("    -D <int>: échelle du dessin (longueur d'un trait) [défaut: 5]");
        System.err.println("    -x <int>: position horizontale initiale [défaut: centre de la fenêtre]");
        System.err.println("    -y <int>: position verticale initiale [default: bas de la fenêtre]");
        System.err.println("    -a <double>: angle initial [défaut: 270]");
        System.err.println("    -delta <double>: angle [défaut: 90]");
        System.exit(1);
    }
    
    public static double toDouble(String value, String errorMessage) {
        try {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            System.err.println("Erreur: " + errorMessage);
            System.exit(1);
        }
        return Double.NaN; // UNREACHABLE
    }
    
    public void parse(String[] args) {
        if (args.length < 2) {
            usage();
        }
        
        int i = 0;
        while (i < args.length) {
            if (args[i].charAt(0) == '-') {
                options.put(args[i], toDouble(args[i + 1], args[i] + " doit être un double"));
                i += 2;
            }
            else {
                arguments.add(args[i]);
                ++i;
            }
        }
    }

    public double get(String key, double defaultValue) {
        if (options.containsKey(key))
            return options.get(key);
        else
            return defaultValue;
    }

    public void addRules(RuleSet rs) {
        int i = 2;
        try {
            for (i = 2; i < arguments.size(); ++i) {
                char key = arguments.get(i).charAt(0);
                String value = arguments.get(i).substring(2);
                rs.addRule(key, value);
            }
        }
        catch (InvalidRuleException e) {
            System.err.printf("Erreur: '%s' n'est pas une règle valide\n", arguments.get(i));
            System.exit(1);
        }
    }
}
