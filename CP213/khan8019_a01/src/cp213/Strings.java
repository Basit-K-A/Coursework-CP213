package cp213;

/**
 * @author Basit Khan 169058019
 * @version 2024-09-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	Boolean isP = true;
	
	String words = string.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+", "").toLowerCase();
	System.out.println(words);
	
	int i = 0;
	int j = words.length()-1;
	
	while (i < j) {
	    if(words.charAt(i) != words.charAt(j)) {
		isP = false;
	    }
	    i++;
	    j--;
	}

	return isP;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	Boolean isV = true;
	
	if (name.startsWith("_")) {
	    if (name.length() <= 1 || name.matches(".*\\d.*")) {
		isV = false;
	    }
	
	}
	else if (Character.isLetter(name.charAt(0))) {
	    if (name.matches(".*\\d.*")) {
		isV = false;
	    }
	}
	else {
	    isV = false;
	}

	return isV;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
	String result = "";
        if (word == null || word.isEmpty()) {
            result = word;
        }

        if (VOWELS.indexOf(word.charAt(0)) != -1) {
            return word + "way";
        }

        int firstVowelIndex = -1;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (VOWELS.indexOf(c) != -1 || (i != 0 && (c == 'y' || c == 'Y'))) {
                firstVowelIndex = i;
                break;
            }
        }

        if (firstVowelIndex == -1) {
            return word + "ay";
        }

        String consonantPrefix = word.substring(0, firstVowelIndex);
        String restOfWord = word.substring(firstVowelIndex);
        
        result = restOfWord + consonantPrefix + "ay";

        if (Character.isUpperCase(word.charAt(0))) {
            result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
        }

        return result;
}
}
