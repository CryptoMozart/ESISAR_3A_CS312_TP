.SUFFIXES: .java .class

.java.class:
	javac -g -d class/ $*.java

CLASSES = \
    Boisson.java \
    Carte.java \
    Commande.java \
    Consommable.java \
	Dessert.java \
	Entree.java \
	Menu.java \
	Nutrition.java \
	Plat.java \
	PlatPrincipal.java \
	TestRestaurant.java \
	Parser.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm -rf  class/
