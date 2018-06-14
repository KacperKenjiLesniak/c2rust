package transpiler;

import app.Controller;
import org.junit.Test;

import static org.junit.Assert.*;

public class TranspilerTest {
    Transpiler transpiler = new Transpiler();
    Controller controller = new Controller();

    @Test
    public void shouldTranspilerDeclaration(){
        String assignment = transpiler.transpile("int a = 5;");

        assertEquals("let a = 5;\n", assignment);
    }

    @Test
    public void shouldTranspileAssignment(){
        String assignment = transpiler.transpile("int main(){a = 6;}");

        assertEquals("fn main(){\na=6;\n}", assignment);
    }

    @Test
    public void shouldTranspileArrayInitialization(){
        String assignment = transpiler.transpile("int a[2] = {2, 1};");

        assertEquals("let a = [2, 1];\n", assignment);
    }

    @Test
    public void shouldTranspileMainFunction(){
        String assignment = transpiler.transpile("int main(){};");

        assertEquals("fn main(){\n\n}", assignment);
    }

    @Test
    public void shouldTranspileIfStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5){a=6;}}");

        assertEquals("fn main(){\nif a == 5 {\na=6;\n}\n}", assignment);
    }

    @Test
    public void shouldTranspileIfAndStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5 && a < 6 && a > 0){a = 6;}}");

        assertEquals("fn main(){\nif a == 5 && a < 6 && a > 0 {\na=6;\n}\n}", assignment);
    }

    @Test
    public void shouldTranspileIfElseStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5){a=6;}else a=7;}");

        assertEquals("fn main(){\nif a == 5 {\na=6;\n}\nelse a=7;\n}", assignment);
    }

    @Test
    public void shouldTranspileForWithStep(){
        String code = transpiler.transpile("int main() {\n" +
                "  for (int a = 5; a > 0; a = a -2) {\n" +
                "  }\n" +
                "}");
        code = controller.createImports() + "\n" + code;

        assertEquals("use std::iter::rev;\n" +
                "use std::iter::step_by;\n" +
                "\n" +
                "fn main(){\n" +
                "for a in (1..6).rev().step_by(2){\n" +
                "\n" +
                "}\n" +
                "\n" +
                "}", code);
    }
}