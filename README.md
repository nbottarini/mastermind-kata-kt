Mastermind or Master Mind is a code-breaking game for two players. The modern game with pegs was invented in 1970 by Mordecai Meirowitz, an Israeli postmaster and telecommunications expert. It resembles an earlier pencil and paper game called Bulls and Cows that may date back a century or more. (Source Wikipedia)

## Game rules:

- The Mastermind (computer) will select 4 colours. The colours are randomly selected from["Red", "Blue", "Green", "Orange", "Purple", "Yellow"]. Colours can be duplicated but there will always be exactly 4.
- You enter your guess (4 colours) and the Mastermind returns a result to you. For every correctly positioned colour an element (peg) of “Black” is returned. For every correct colour but in the wrong position an element of “White” will be returned.
- Passing the correct combination will return "WON!".
- You have 10 chances to guess the combination

## App:

- Build a console app
- You have to enter each guess (4 colours) and the computer prints the result (blacks and whites)
- If you reach the 10 attempts the program prints GAME OVER and exits
- If you guess the number the program prints YOU WON and exits
- If you enter invalid input (unknown colours or bad amount) the program prints an error

## Examples:

Assuming correct combination is "red, blue, green, yellow"

```
Welcome to Mastermind! 
Try to guess the combination using the colours (red, blue, green, orange, purple, yellow):
> red, orange, yellow, orange
black, white
```

Assuming correct combination is "red, blue, green, black"

```
Welcome to Mastermind! 
Try to guess the combination using the colours (red, blue, green, orange, purple, yellow):
> red, blue, yellow, green
black, white, white
> red, yellow, green, yellow
black, white
```