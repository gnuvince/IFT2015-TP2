package plante;

import java.util.HashMap;
import java.util.ArrayList;


@SuppressWarnings("serial")
class InvalidRuleException extends Exception {
}


public class RuleSet {
    // The list of accepted keys.
    private static String VALID_CHARACTERS = "fF+-[]X";
    
    // A mapping Char -> [String] where Char is one of the
    // valid characters and [String] is a list (ArrayList)
    // of the different rules into which Char maps.
    private HashMap<Character, ArrayList<String>> rules = 
        new HashMap<Character, ArrayList<String>>();
    
    
    // Return if the character is in the alphabet.
    public static boolean isCharValid(Character c) {
        return VALID_CHARACTERS.indexOf(c) != -1;
    }
    
    // Return if a string is sequence of characters in the alphabet.
    public static boolean isRuleValid(String s) {
        for (int i = 0; i < s.length(); ++i)
            if (!isCharValid(s.charAt(i)))
                return false;
        return true;
    }
    
    // Add a rule:
    //  - if key already exists, add to the ArrayList;
    //  - if key doesn't exist, create a new ArrayList containing value
    //    and add it to the HashMap.
    public void addRule(Character key, String value) throws InvalidRuleException {
        if (!isCharValid(key) || !isRuleValid(value))
            throw new InvalidRuleException();
        
        ArrayList<String> rightSide;
        if (rules.containsKey(key)) 
            rightSide = rules.get(key);
        else {
            rightSide = new ArrayList<String>();
            rules.put(key, rightSide);
        }
        
        if (!rightSide.contains(value))
            rightSide.add(value);
    }
    
    // Get the string from the HashMap:
    //   - if a rule does not exist, return the character,
    //   - if a rule exists, return a random element from the ArrayList.
    public String getString(Character c) {
        if (rules.containsKey(c)) {
            ArrayList<String> rightSide = rules.get(c);
            int n = rightSide.size();
            int i = (int)(Math.random() * n);
            return rightSide.get(i);
        }
        else
            return c.toString();
    }
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Character key: rules.keySet()) {
            sb.append(key + ": " + rules.get(key) + "\n");
        }
        return sb.toString();
    }
}
