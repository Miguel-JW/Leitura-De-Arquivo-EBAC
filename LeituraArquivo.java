import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraArquivo {

    private static final String ARQUIVO_ENTRADA = "funcionarios.txt";
    private static final String ARQUIVO_XML     = "funcionarios.xml";
    private static final String SEPARADOR       = ";";

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("     📄  Leitura de Arquivo → XML          ");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── PASSO 1: Lê o arquivo e transforma em objetos ─────
        List<Funcionario> funcionarios = lerArquivo();
        if (funcionarios.isEmpty()) return;

        // ── PASSO 2: Exibe os objetos no console ───────────────
        System.out.println("\n── Dados lidos do arquivo ────────────────────");
        for (Funcionario f : funcionarios) {
            System.out.println("  " + f);
        }

        // ── PASSO 3: Gera XML e exibe no console ───────────────
        String xml = gerarXml(funcionarios);
        System.out.println("\n── Conteúdo XML gerado ───────────────────────");
        System.out.println(xml);

        // ── PASSO 4: Salva o XML em arquivo ───────────────────
        salvarXml(xml);
    }

    // ── Lê o arquivo linha por linha e cria objetos ────────
    static List<Funcionario> lerArquivo() {
        List<Funcionario> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ENTRADA))) {
            String linha;
            int numLinha = 0;

            System.out.println("\n── Lendo arquivo: " + ARQUIVO_ENTRADA + " ───────────");

            while ((linha = br.readLine()) != null) {
                numLinha++;
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split(SEPARADOR);

                // Valida se a linha tem os 4 campos esperados
                if (partes.length != 4) {
                    System.out.println("⚠  Linha " + numLinha + " inválida, ignorada: " + linha);
                    continue;
                }

                try {
                    int    id      = Integer.parseInt(partes[0].trim());
                    String nome    = partes[1].trim();
                    String cargo   = partes[2].trim();
                    double salario = Double.parseDouble(partes[3].trim());

                    lista.add(new Funcionario(id, nome, cargo, salario));
                    System.out.println("  ✔  Linha " + numLinha + " lida com sucesso.");

                } catch (NumberFormatException e) {
                    System.out.println("⚠  Erro ao converter linha " + numLinha + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("⚠  Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("  Total de objetos criados: " + lista.size());
        return lista;
    }

    // ── Gera a string XML completa ─────────────────────────
    static String gerarXml(List<Funcionario> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<funcionarios>\n");
        for (Funcionario f : lista) {
            sb.append(f.toXml()).append("\n");
        }
        sb.append("</funcionarios>");
        return sb.toString();
    }

    // ── Salva o XML gerado em um arquivo ───────────────────
    static void salvarXml(String xml) {
        try (FileWriter fw = new FileWriter(ARQUIVO_XML)) {
            fw.write(xml);
            System.out.println("\n✔  Arquivo XML salvo como: " + ARQUIVO_XML);
        } catch (IOException e) {
            System.out.println("⚠  Erro ao salvar XML: " + e.getMessage());
        }
    }
}
