# 🔢 Calculator Applet using Swing in Java

### Why

The college I am studying at has a Java course, which teaches about Java applets and Java swing features. Although deprecated, those frameworks were fun to fiddle around with
and helped understand how event listeners and handlers worked. 

### How to run

Since HTML5 has dropped support for running Java applets in a browser, it is recommended to use an extension called [CheerpJ Applet Runner](https://chrome.google.com/webstore/detail/cheerpj-applet-runner/bbmolahhldcbngedljfadjlognfaaein?hl=en)
to run those programs. Install said extension on your chromium based browser, open [the website for the calculator](https://thebrahmnicboy.github.io/swing-calc/) and run the extension.



#### Functions 

- Enter Numbers using the numpad.
- Enter deicmal point using the '.' key.
- "<-" is backspace, and works the same as "CE", both of which delete one element from the right.
- "C" clears the display.
- "SQ" gives the square root of number in display, but gives an error if it isn't a number.
- "MC" is memory clear. It clears the memory.
- "MR" is memory recall. It fills the display with the contents of the memory.
- "MS" is memory save. It saves the number on the display into the memory.
- "M+" and "M-" add/subtract the number in diplay from memory.

### Known issues

- Currently, all the functions of the calculator work, with the exception of the equals button, which is supposed to parse the expression and evaluate it.
It might be a problem with HTML5 and the extension not being able to correctly translate the code over, but I _**will not**_ be looking into it, since it is a deprecated framework.

- The rendering of some buttons like the Percentage, the M+, the +/- is not handled correctly by CheerpJ Applet Runner. They still function as normal.
