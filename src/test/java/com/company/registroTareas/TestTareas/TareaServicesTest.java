package com.company.registroTareas.TestTareas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import com.company.registroTareas.dao.ITaskDao;
import com.company.registroTareas.model.Tareas;
import com.company.registroTareas.services.TaskServiceImpl;


class MyList extends AbstractList<String> {
    @Override
    public String get(final int index){
        return null;
    }
    @Override
    public int size(){
        return 0;
    }
}

class TareaServicesTest {

	
	@Mock
    private ITaskDao tareaRepository;
    @InjectMocks
    private TaskServiceImpl tareaService;
    private List<Tareas> tareaList = new ArrayList<Tareas>();
    private Tareas tareas;
    Tareas newtarea = new Tareas();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        int id = 0;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        for(int j = 0; j < 10; j ++)
        {
        	tareas=new Tareas();
        	tareas.setId(new Long(id));
        	tareas.setDescripcion("Descripcion"+id);
        	tareas.setFechaCreación(date);
        	tareas.setEstado(true);
        	tareaList.add(tareas);
            id++;
        }
        newtarea.setId(new Long(10));
        newtarea.setDescripcion("Descripcion10");
        newtarea.setFechaCreación(date);
        newtarea.setEstado(true);
    }

    @Test
    void findAll() {
        when(tareaRepository.findAll()).thenReturn(tareaList);
        for (int i=0;i<tareaList.size();i++) {System.out.println(tareaRepository.findAll());}
        assertNotNull(tareaRepository.findAll());
    }

    @ParameterizedTest
    @ValueSource(longs = {7,8})
    void findById(Long Id) {
        when(tareaRepository.findById(Id)).thenReturn(Optional.ofNullable(tareaList.get(Math.toIntExact(Id))));
        System.out.println(Id);
        System.out.println(tareaRepository.findById(Id).get().getDescripcion());
        assertNotNull(tareaRepository.findById(Id));
    }

    @Test
    void save() {
        when(tareaRepository.save(tareas)).thenReturn(newtarea);
        tareaList.add(newtarea);
        System.out.println(tareaRepository.save(tareas).getId());
        System.out.println(tareaRepository.save(tareas).getDescripcion());
        System.out.println(tareaRepository.save(tareas).getFechaCreación());
        System.out.println(tareaRepository.save(tareas).isEstado());
        assertNotNull(tareaRepository.save(tareas));
        System.out.println("Lista actualizada");
        for (int i=0;i<tareaList.size();i++) {System.out.println(tareaList.get(i).getDescripcion());}
    }

    @ParameterizedTest
    @ValueSource(longs = {5})
    void delete(Long Id) {
        Tareas tareaRemove = null;
        for (int x = 0; x < tareaList.size(); x++) {tareaRemove = tareaList.get(Math.toIntExact(Id));}
        when(tareaRepository.existsById(Id)).thenReturn(tareaList.remove(tareaRemove));
        if (!tareaList.remove(tareaRemove)) {System.out.println("No se elimino exitosamente");
        } else {System.out.println("Si se elimino exitosamente");}
        assertNotNull(tareaList.remove(tareaRemove));
        System.out.println("Lista actualizada");
        for (int i = 0; i < tareaList.size(); i++) {System.out.println(tareaList.get(i).getDescripcion());}
    }
}
