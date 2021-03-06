# Single-Layer-Network-perceptron
Single Layer Network of perceptrons algorithm implemented in Java
---------
TASK
---------
The aim of the project is to create a single-layer netowrk to identify the language an input
text is written in.
The lang.zip file contains a set of texts written in three languages – English, Polish
and German. To classify a given text, count the number of occurences of each letter of the
latin alphabet. Ignore all other characters – only count the frequencies of the 26 letters
of the latin alphabet.
For each text, generate a 26-element input vector containing the number of occurences
of each letter. Then normalize it:
vˆ =
v
|v|
.
The output of the network should have local representation: one neuron should be
assigned to each language. For a given text only the appropriate neuron should have
value 1 and all others 0. To classify an input text, select the neuron with the maximum
activation. The discrete (’step’) activation function can be used (in this case you can
reuse the implementation of the perceptron) or the sigmoid.
The lang.test.zip contains the test set to determine accuracy.
The program should provide a UI to input a new text.
