package navid.usermanagementsys.controller;


import navid.usermanagementsys.service.JpaService.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


public abstract class CRUD_REST_Controller<T> {

    private CRUDService<T> crudService;

    @Autowired
    public void setCrudService(CRUDService<T> crudService) {
        this.crudService = crudService;
    }

    @RequestMapping(value = "listAll" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Page<T> listAllJson(Pageable pageable){
        return crudService.listAllByPage(pageable);
    }

    @RequestMapping(value = "show/{id}" , method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public T showJson(@PathVariable Integer id){
        return crudService.getById(id);
    }

    @RequestMapping(value = "update" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public T updateJson(@RequestBody T t){
        return crudService.saveOrUpdate(t);
    }

    @RequestMapping(value = "create" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public T createJson(@RequestBody T t){
        return crudService.saveOrUpdate(t);
    }

    @RequestMapping(value = "delete/{id}" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public boolean deleteJson(@PathVariable Integer id){
        try {
            crudService.delete(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
