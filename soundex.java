import java.util.Scanner;

public class soundex {


    /** * This is a simple description of the method isAlphaWord. 
     * @param word takes a word as a parameter.
     * @return boolean true if all characters are alphabetic, false if not.
    */

    public static boolean isAlphaWord(String word){
        char character;
        for (int i = 0; word.length() > i ; i++){
            character = word.charAt(i);
            if ( character == '1' || character == '2' || character == '3' || character == '4' || character == '5' || character == '6' || character == '7' || character == '8' || character == '9' || character == '0'){
                return false;
            }
        }
        return true;
    }

    /** * This is a simple description of the method getCode.
     * @param character takes a character as a parameter.
     * @return returns the integer number representing the given character. If the character is not one of the coded characters, returns -1. 
    */    

    public static int getCode(char character){
        
        /*
        if ( character == 'A' || character == 'E' || character == 'I' || character == 'O' || character == 'U' || character == 'H' || character == 'W' || character == 'Y'){
            return (0);
        }
        */
        if(character == 'B' || character == 'F' || character == 'P' || character == 'V'){
            return (1);
        }
        else if ( character == 'C' || character == 'G' || character == 'J' || character == 'K' || character == 'Q' || character == 'S' || character == 'X' || character == 'Z'){
            return (2);
        }
        else if(character == 'D' || character == 'T'){
            return (3);
        }
        else if(character == 'L'){
            return (4);
        }
        else if(character == 'M' || character == 'N'){
            return (5);
        }
        else if(character == 'R'){
            return (6);
        }
        return (-1);
    }

    /** * This is a simple description of the method buildCode.
     * @param word takes word as a parameter.
     * @return newWord where each character has been changed to its corresponding code value. If no code value exists (vowels or hwy), concatenate the original character. 
    */    
    
    public static String buildCode(String word){
        String newWord = "";
        for (int i = 0; word.length() > i ; i++){
            if (getCode(word.charAt(i)) != -1){
                newWord = newWord + getCode(word.charAt(i));
            }
            else{
                newWord = newWord + word.charAt(i);
            }
        }
        return (newWord);
    }



    /** * This is a simple description of the method removeAdjacentDuplicates.
     * @param word takes a String as a parameter.
     * @return newWord for all (adjacent/side by side) repeating characters, keeps the first but removes the rest.
    */    
    public static String removeAdjacentDuplicates(String word){
        String newWord = "";
        newWord = newWord + word.charAt(0);
        for (int i = 1; word.length() > i ; i++){
            if (newWord.charAt(newWord.length()-1) != word.charAt(i)){
                newWord = newWord + word.charAt(i);
            }
        }
        return (newWord);
    }


    /** * This is a simple description of the method removeLetters.
     * @param updateWord, lettersToRemove takes 2 strings as parameters, the first is the string to update, and the second is a string containing the set of letters to remove.
     * @return newWord returns a new string where all letters that appear in the given set of letters have been removed.
    */    
    public static String removeLetters(String updateWord, String lettersToRemove){
        String newWord = "";
        for (int i = 0; updateWord.length() > i ; i++){
            for (int j = 0; lettersToRemove.length() > j; j++){
                if(updateWord.charAt(i) == lettersToRemove.charAt(j)){
                    break;
                }
                else if( j == lettersToRemove.length() - 1){
                    newWord = newWord + updateWord.charAt(i);
                }
            }
        }
        return (newWord);
    } 


    /** * This is a simple description of the method padCode.
     * @param code takes a String as a parameter.
     * @return if the code passed as a parameter has fewer than 4 characters, pads the remaining spaces with zeros. If the code has more than 4 characters, returns the first 4 characters as a string.
    */   
    public static String padCode(String code){
        int zeroNumber = 0;
       if (code.length() > 4){
           return(code.substring(0, 4));
       }
       else if (code.length() < 4){
           zeroNumber = 4 -  code.length();
           for (int i = zeroNumber; i>0; i--){
               code = code + "0";
           }
       }
       return(code);
    }

    /** * This is a simple description of the method padCode.
     * @param word takes a word as a parameter
     * @return newWord returns its soundex encoding. Return an empty string if there are any non-alpha characters in the word.
    */   
    public static String getSoundex(String word){
        String newWord = "";

        if (isAlphaWord(word)){
            word = removeAdjacentDuplicates(word.toUpperCase());
            newWord = newWord + word.charAt(0);
            word = word.substring(1);
            word = buildCode(word);
            word = removeAdjacentDuplicates(word);
            word = removeLetters(word,"AEIİOUHWY");
            newWord = newWord.toUpperCase() + word;
            newWord = padCode(newWord);


        }
        return (newWord);
    }


    public static void main(String[] args) {
        String word="";
        String soundexWord;
        
        Scanner scanner = new Scanner(System.in);
        while (word.compareToIgnoreCase("exit") != 0){
            System.out.print("Enter a string: ");
            word = scanner.nextLine();
            soundexWord = getSoundex(word);
            if (soundexWord.equalsIgnoreCase("")){
                System.out.println("Characters must be alphabetic...");
            }
            else{
                System.out.println("Soundex: " + soundexWord);
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
        scanner.close();
    }
}
