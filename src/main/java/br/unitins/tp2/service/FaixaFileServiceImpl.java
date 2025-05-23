package br.unitins.tp2.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.tp2.model.Faixa;
import br.unitins.tp2.repository.FaixaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class FaixaFileServiceImpl implements FileService {
    // ex. /user/janio/quarkus/images/usuario/
    private final String PATH_USER = System.getProperty("user.home")
        + File.separator + "quarkus"
        + File.separator + "images"
        + File.separator + "faixa" + File.separator;

    @Inject
    FaixaRepository faixaRepository;

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem)  throws IOException {
        Faixa faixa = faixaRepository.findById(id);

        try {
            String novoNomeImagem = salvarImagem(imagem, nomeImagem);
            faixa.setNomeImagem(novoNomeImagem);
            // excluir a imagem antiga (trabalho pra quem????)
        } catch (IOException e) {
            throw e;
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {
        
        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada.");
        }

        // verificando o tamanho do arquivo, não permitor maior que 10 megas
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
        String nomeArquivo = UUID.randomUUID()
        +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // alunos (melhorar :)
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        // criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    
    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    }

    public static void main(String[] args) {
        FaixaFileServiceImpl f = new FaixaFileServiceImpl();
        System.out.println(f.download("faixa_preta_judo.png").getName());
    }
    
}
