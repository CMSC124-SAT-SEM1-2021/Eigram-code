import java.io.*;
import java.util.*;

class lexicalA {
    static char[] lexeme = new char[10]; //lex array of size 10
	static char lexChar; //lex char
	static int lexLen; //lex length
	static FileInputStream stdin; //to read file
	static int i; //integer for char

	//char classifications
    static int charClass;
	final static int LETTER = 0; //a-z, A-Z
	final static int DIGIT = 1; //0-9
	final static int UNKNOWN = 2; //+, -, *, /, (, ), =
    final static int EOF = 3;

	//tokens
	static String token;
	final static String IDENT = "IDENTIFIER"; //LETTER
    final static String INT_LIT = "INT_LITERAL"; //DIGIT
	final static String PLUS = "PLUS_OP"; //UNKNOWN +
	final static String MINUS = "MINUS_OP"; //UNKNOWN -
	final static String MULT = "MULT_OP"; //UNKNOWN *
	final static String DIV = "DIV_OP"; //UNKNOWN /
	final static String LEFT_P = "LEFT_PAREN"; //UNKNOWN (
	final static String RIGHT_P = "RIGHT_PAREN"; //UNKNOWN )
    final static String EQUAL = "ASSIGN_OP"; //UNKNOWN =

    /* readFile() reads input from file */
    void readFile(String input) {
        try {
			stdin = new FileInputStream(input);
		} catch (FileNotFoundException exc) {
			System.out.println("File not found");
			return;
		}
		
        System.out.printf("Lexeme \t Token\n");
        System.out.printf("------ \t -----\n");
		getChar();
		do {
		 	lex();
		} while (token != "EOF");
		
		try {
			stdin.close();
		} catch (IOException exc) {
			System.out.println("Error"); //did not clsoe file
		}
    }

    /* getChar() gets a chracter from a file named inpu.txt and
    classifies it as LETTER, DIGIT, or UNKNOWN */
	static void getChar() {
		try { //read character
			i = stdin.read(); //integer value of char read
		} catch (IOException exc) {
			System.out.println("Error"); //did not read file
		}
		
		lexChar = (char)i; //cast to get actual char read

		if (i != -1) { //-1 is EOF
			if (isLetter(lexChar)) { //a-z, A-Z
			    charClass = LETTER;
		    }
			else if (isDigit(lexChar)) { //0-9
				charClass = DIGIT;
			} 
			else { //+, -, *, /, (, ), =
				charClass = UNKNOWN;
			}
		} else {
			charClass = EOF;
		}
	}

    /* addChar() adds the character to a lexeme array of
    size 10 (therefore, maximum lexeme length is 9 to accommodate
    for string terminator '\0'). if longer than max length, simply
    disregard the extra characters (do not insert in array) */
	static void addChar() {
		if (lexLen <= 8) {
			lexeme[lexLen++] = lexChar;
			lexeme[lexLen] = '\0';
		} else {
			System.out.printf("Error"); //lexeme too long
		}
	}

    /* lookup() returns a token for unknown symbols; the valid
    unknown symbols are +, -, *, /, (, ), and =. Invalid symbols
    all have the EOF token. */
	static String lookup(char c) {
		switch (c) {
            case '+':
                addChar();
                token = PLUS;
                break;

            case '-':
				addChar();
				token = MINUS;
				break;

            case '*':
				addChar();
				token = MULT;
				break;

			case '/':
				addChar();
				token = DIV;
				break;

			case '(':
				addChar();
				token = LEFT_P;
				break;

			case ')':
				addChar();
				token = RIGHT_P;
				break;

            case '=':
				addChar();
				token = EQUAL;
				break; 

			default:
				addChar();
				token = "EOF";
				break;
		}

		return token;
	}

	/* getUntilSpace() getChar until space */
	static void getUntilSpace() {
		while (lexChar == ' ') {
			getChar();
		}
	}

	/* lex() lexical analyzer */
	static String lex() {
		String lexStr; 
		lexLen = 0;

		getUntilSpace();
		switch (charClass) {
			case LETTER: //a-z, A-Z
				addChar();
				getChar();
				
                //lexemes starting with a LETTER can consist of DIGITS
				while (charClass == LETTER || charClass == DIGIT) {
					addChar();
					getChar();
				}
				token = IDENT;
				break;

			case DIGIT: //0-9
                addChar();
			    getChar();
				
                //lexemes that start iwth a DIGIT only contain digits
				while (charClass == DIGIT) {
					addChar();
					getChar();
				}
				token = INT_LIT;
				break;

			case UNKNOWN: //+, -, *, /, (, ), =
				lookup(lexChar);
				getChar();
				break;

			case EOF: //EOF
				token = "EOF";
				lexeme[0] = 'E';
				lexeme[1] = 'O';
				lexeme[2] = 'F';
				lexeme[3] = '\0';
			 	System.exit(0); //terminate
        }

		lexStr = new String(lexeme).split("\0")[0];
		 
		System.out.printf("%s \t %s\n", lexStr, token);
		return token;
	}

    /* isLetter() checks if char is letter */
    static boolean isLetter(char l) {
        if (l >= 'a' && l <= 'z' || l >= 'A' && l <= 'Z') {
            return true;
        } else {
            return false;
        }  
    }

    /* isDigit() checks if char is digit */
    static boolean isDigit(char d) {
        if ((d == '0') || (d == '1') || (d == '2') || (d == '3') || (d == '4') ||
        (d == '5') || (d == '6') || (d == '7') || (d == '8') || (d == '9')){
            return true;
        } else {
            return false;
        }
    }
}

public class lexicalAnalyzer {
	public static void main(String[] args) throws IOException {		
		String input = "input.txt";

        lexicalA lex = new lexicalA();
        lex.readFile(input);
	}
}