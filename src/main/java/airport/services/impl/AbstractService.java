package airport.services.impl;

import airport.dtos.AbstractDto;
import airport.entities.AbstractEntity;
import airport.mappers.Mapper;
import airport.services.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractService
        <E extends AbstractEntity<ID>,
        DTO extends AbstractDto<ID>,
        ID extends Serializable>
        implements Service<DTO, ID> {

    protected abstract JpaRepository<E, ID> getRepository();
    protected abstract Mapper<E, DTO, ID> getMapper();

    @Override
    public long countAll() {
        return getRepository().count();
    }

    @Override
    public DTO getById(ID id) {
        E entity = getEntityByIdOrThrow(id);
        return getMapper().toDto(entity);
    }

    @Override
    public Page<DTO> getAll(Pageable pageable) {
        return getRepository()
                .findAll(pageable)
                .map(getMapper()::toDto);
    }

    @Override
    public Collection<DTO> getAllById(Collection<ID> idCollection) {
        return getRepository()
                .findAllById(idCollection)
                .stream()
                .map(getMapper()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DTO create(DTO dto) {
        var entity = getMapper().toEntity(dto);
        entity = getRepository().save(entity);
        return getMapper().toDto(entity);
    }

    @Override
    public DTO save(ID id, DTO dto) {
        dto.setId(id);
        return create(dto);
    }

    @Override
    public Collection<DTO> saveAll(Collection<DTO> dtoCollection) {
        var entityCollection = dtoCollection
                .stream()
                .map(getMapper()::toEntity)
                .collect(Collectors.toList());

        return getRepository()
                .saveAll(entityCollection)
                .stream()
                .map(getMapper()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAllById(Collection<ID> idCollection) {
        var entityCollection = idCollection
                .stream()
                .map(getRepository()::getOne)
                .collect(Collectors.toList());

        getRepository().deleteAll(entityCollection);
    }

    protected E getEntityByIdOrThrow(ID id) {
        return getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        String.format("Entity with id '%s' was not found", id)
                )
        );
    }

}
