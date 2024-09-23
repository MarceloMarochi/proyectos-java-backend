{

                            Online Pascal Compiler.
                Code, Compile, Run and Debug Pascal program online.
Write your code in this editor and press "Run" button to execute it.

}

{$mode objfpc}
{$m+}

program Hello;

type

    Base = class
        public
            procedure Proc;
    end;

    Derivada = class(Base)
        public
            procedure Proc;
    end;

    PBase = ^Base;
    PDerivada = ^Derivada;


procedure Base.Proc;
begin
    writeln('base');
end;

procedure Derivada.Proc;
begin
    writeln('derivada');
end;


var
    b: Base;
    d: Derivada;
    pb: PBase;
    pd: PDerivada;

begin
  writeln ('Hello World');

  b := Base.Create;
  d := Derivada.Create;

  b.Proc;
  d.Proc;
  b := d;
  b.Proc;
  (b as Derivada).Proc;

end.