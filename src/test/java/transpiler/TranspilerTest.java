package transpiler;

import app.Controller;
import org.junit.Test;

import static org.junit.Assert.*;

public class TranspilerTest {
    private Transpiler transpiler = new Transpiler();
    private Controller controller = new Controller();

    @Test
    public void shouldTranspilerDeclaration(){
        String assignment = transpiler.transpile("int a = 5;");

        assertEquals("let a = 5;\n", assignment);
    }

    @Test
    public void shouldTranspileAssignment(){
        String assignment = transpiler.transpile("int main(){a = 6;}");

        assertEquals("fn main(){\na=6;\n}\n", assignment);
    }

    @Test
    public void shouldTranspileArrayInitialization(){
        String assignment = transpiler.transpile("int a[2] = {2, 1};");

        assertEquals("let a = [2, 1];\n", assignment);
    }

    @Test
    public void shouldTranspileMainFunction(){
        String assignment = transpiler.transpile("int main(){};");

        assertEquals("fn main(){\n}\n", assignment);
    }

    @Test
    public void shouldTranspileIfStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5){a=6;}}");

        assertEquals("fn main(){\nif a == 5 {\na=6;\n}\n}\n", assignment);
    }

    @Test
    public void shouldTranspileIfAndStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5 && a < 6 && a > 0){a = 6;}}");

        assertEquals("fn main(){\nif a == 5 && a < 6 && a > 0 {\na=6;\n}\n}\n", assignment);
    }

    @Test
    public void shouldTranspileIfElseStatement(){
        String assignment = transpiler.transpile("int main(){if(a==5){a=6;}else a=7;}");

        assertEquals("fn main(){\nif a == 5 {\na=6;\n}\nelse a=7;\n}\n", assignment);
    }
    @Test
    public void shouldTranspileFunctionDefinition() {
        String code = transpiler.transpile("int timesTwo(int a){\n" +
                "  return a*2;\n" +
                "}\n" +
                "\n" +
                "int main(){\n" +
                "\tint b = timesTwo(2);\n" +
                "}");

        assertEquals("fn timesTwo(a: i32) -> i32\n" +
                "{\n" +
                "a*2\n" +
                "}\n" +
                "\n" +
                "fn main(){\n" +
                "let b = timesTwo(2);\n" +
                "}\n", code);
    }

    @Test
    public void shouldTranspileFor(){
        String code = transpiler.transpile("int main() {\n" +
                "  for (int a = 0; a < 5; a = a + 1) {\n" +
                "  }\n" +
                "}");

        assertEquals("fn main(){\n" +
                "for a in 0..5{\n" +
                "}\n" +
                "}\n", code);
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
                "for a in (1..6).rev().step_by(2){" +
                "\n" +
                "}\n" +
                "}\n", code);
    }
    @Test
    public void shouldTranspileStringInitialization(){
        String assignment = transpiler.transpile("char a[] = \"test\";");

        assertEquals("let a = \"test\";\n", assignment);
    }

    @Test
    public void shouldAddTwoStringsTogether(){
        String code = transpiler.transpile("int main(){char str[30] = \"Ala \";const char *strFrom = \"ma kota\";strcat (str, strFrom);}");

        assertEquals("fn main(){\n" +
                "let str = \"Ala \";\n" +
                "let strFrom = \"ma kota\";\n" +
                "let newstr = str.to_string() + strFrom;\n" +
                "}\n", code);
    }

    @Test
    public void shouldPrintOutString(){
        String code = transpiler.transpile("int main(){printf(\"Test\");}");

        assertEquals("fn main(){\n" +
                "print!(\"Test\");\n" +
                "}\n", code);
    }

    @Test
    public void shouldPrintOutStringWithNewLine(){
        String code = transpiler.transpile("int main(){printf(\"Test\\n\");}");

        assertEquals("fn main(){\n" +
                "println!(\"Test\");\n" +
                "}\n", code);
    }

    @Test
    public void shouldTranspileStructDefinition(){
        String code = transpiler.transpile("struct tests{ int a; float b;};");

        assertEquals("struct tests {\n" +
                "a:i32,\n" +
                "b:f32,\n" +
                "}\n", code);
    }


}