public class Funcionario {

    private int    id;
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(int id, String nome, String cargo, double salario) {
        this.id      = id;
        this.nome    = nome;
        this.cargo   = cargo;
        this.salario = salario;
    }

    public int    getId()      { return id; }
    public String getNome()    { return nome; }
    public String getCargo()   { return cargo; }
    public double getSalario() { return salario; }

    // ── Converte o objeto para XML ─────────────────────────
    public String toXml() {
        return String.format(
            "  <funcionario>\n"      +
            "    <id>%d</id>\n"      +
            "    <nome>%s</nome>\n"  +
            "    <cargo>%s</cargo>\n"+
            "    <salario>%.2f</salario>\n" +
            "  </funcionario>",
            id, nome, cargo, salario
        );
    }

    @Override
    public String toString() {
        return String.format("[%d] %-18s | %-12s | R$ %.2f",
                             id, nome, cargo, salario);
    }
}
