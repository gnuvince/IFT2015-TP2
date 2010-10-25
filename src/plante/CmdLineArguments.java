package plante;

import java.util.ArrayList;
import java.util.HashMap;

public class CmdLineArguments {
    public HashMap<String, Double> options = new HashMap<String, Double>();
    public ArrayList<String> arguments = new ArrayList<String>();

    public void parse(String[] args) {
        int i = 0;
        while (i < args.length) {
            if (args[i].charAt(0) == '-') {
                options.put(args[i], Double.parseDouble(args[i + 1]));
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

    public void addRules(RuleSet rs) throws InvalidRuleException {
        for (int i = 2; i < arguments.size(); ++i) {
            char key = arguments.get(i).charAt(0);
            String value = arguments.get(i).substring(2);
            rs.addRule(key, value);
        }
    }
}
