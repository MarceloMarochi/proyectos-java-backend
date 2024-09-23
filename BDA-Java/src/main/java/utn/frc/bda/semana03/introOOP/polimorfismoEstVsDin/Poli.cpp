/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

class Base {
    public:
        void f() {
            std::cout<<"base\n";
        }
};

class Derivada {
    public:
        void f() {
            std::cout<<"derivada\n";
        }
};

int main()
{
    std::cout<<"Hello World\n";

    Base *b = new Base;
    Derivada *d = new Derivada;

    b->f();                 // base
    d->f();                 // derivada

    ((Base*) d)->f();       // base (derivada en Java)

    b = (Base*) d;
    b->f();                 // base (derivada en Java)
    ((Derivada*) b)->f();   // en Java NO se puede

    return 0;
}