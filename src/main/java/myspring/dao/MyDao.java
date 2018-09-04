package myspring.dao;


import myspring.entity.Category;
import myspring.entity.Item;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MyDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> searchItem(String str) {//searching Item by part of name
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> r = query.from(Item.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate, builder.like(r.get("name"), str + "%"));
        query.where(predicate);

        List<Item> result = entityManager.createQuery(query).getResultList();
        return result;}

        public List<Category> searchByCategory(String str) {//searching Item by Category name
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> r = query.from(Category.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate, builder.like(r.get("name"), str));
        query.where(predicate);

        query.select(r.get("items"));
        List<Category> result = entityManager.createQuery(query).getResultList();
        return result;
    }

        public List<Category> getAllCategories() {//get all Category
       /*List<Category> resultList = entityManager.createQuery("from Category c order by c.id desc", Category.class).getResultList();
       return resultList;*/

       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
       CriteriaQuery<Category> q = builder.createQuery(Category.class);
       Root<Category> categoryRoot = q.from(Category.class);
       List<Category> result = entityManager.createQuery(q).getResultList();
       return result;
    }

    public List<Item> getAllItems() {//get all Item
//        List<Item> resultList2 = entityManager.createQuery("from Item c order by c.id desc", Item.class).getResultList();
//        return resultList2;

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> q = builder.createQuery(Item.class);
        Root<Item> itemRoot = q.from(Item.class);
        List<Item> result = entityManager.createQuery(q).getResultList();
        return result;
    }

    public Item createItem (Item item) {//create new Item
        for (Category category : item.getCategories()) {
            entityManager.persist(category);
        }

        entityManager.persist(item);
        return item;
    }

    public void delete(int id) {//remove Item
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }

    public Item update(int id, Item item) {//change the old record to a new one
        Item original = entityManager.find(Item.class, id);
        if (original != null) {
            original.setName(item.getName());
            original.setDescription(item.getDescription());

           for (Category category : original.getCategories()) {
                entityManager.remove(category);
            }
            original.getCategories().clear();

            for (Category category : item.getCategories()) {
                original.getCategories().add(category);
                entityManager.persist(category);
            }
            entityManager.merge(original);
        }
        return original;
    }
}
