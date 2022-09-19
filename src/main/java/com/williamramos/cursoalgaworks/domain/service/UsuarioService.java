package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.UsuarioNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private EntityManager manager;

    public List<Usuario> listar() {
        List<Usuario> Usuarios = repository.findAll();
        if (Usuarios.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
        }
        return repository.findAll();
    }

    public Usuario buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        manager.detach(usuario);
        Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());
        if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioException(String.format("Já Existe um Usuário cadastrado com o e-mail: %s",usuario.getEmail()));
        }
        return repository.save(usuario);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Usuario Usuario = repository.getById(id);
            if (Usuario != null) {
                repository.deleteById(id);
            }
        } catch (EntidadeNaoEncontradaException e) {
            throw new UsuarioNaoEncontradoException(id);
        }
    }

    @Transactional
    public void associarGrupo(Long idusuario, Long idGrupo){
        Usuario usuario = buscar(idusuario);
        Grupo grupo = grupoService.buscar(idGrupo);
        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long idusuario, Long idGrupo){
        Usuario usuario = buscar(idusuario);
        Grupo grupo = grupoService.buscar(idGrupo);
        boolean isRemovido =usuario.removerGrupo(grupo);
        if(!isRemovido){
            throw new NegocioException(String.format("Não existe associacao entre o usuario %s e o grupo %s", usuario.getNome(), grupo.getNome()));
        }
    }


    public void alterarSenha(Long idUsuario, String senhaAtual, String senhaNova){
        Usuario usuario = buscar(idUsuario);
        if(usuario.senhaNaoCoincideCom(senhaAtual)){
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário. ");
        }
        usuario.setSenha(senhaNova);
    }

    public void ativar(Long id){
        Usuario usuario = buscar(id);
        usuario.ativar();
    }
    public void inativar(Long id){
        Usuario usuario = buscar(id);
        usuario.inativar();
    }

}
