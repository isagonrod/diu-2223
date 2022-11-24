package util;

import model.Customer;
import model.CustomerVO;

/**
 * Clase para convertir un objeto de la clase Customer en otro de la clase CustomerVO, y viceversa.
 *
 * @author Isa González
 */
public class CustomerParse {
    public static CustomerVO parseToCustomerVO(Customer customer) {
        CustomerVO customerVO = new CustomerVO();

        customerVO.setDni(customer.getDni());
        customerVO.setNombre(customer.getNombre());
        customerVO.setApellidos(customer.getApellidos());
        customerVO.setDireccion(customer.getDireccion());
        customerVO.setLocalidad(customer.getLocalidad());
        customerVO.setProvincia(customer.getProvincia());

        return customerVO;
    }

    public static Customer parseToCustomer(CustomerVO customerVO) {
        Customer customer = new Customer();

        customer.setDni(customerVO.getDni());
        customer.setNombre(customerVO.getNombre());
        customer.setApellidos(customerVO.getApellidos());
        customer.setDireccion(customerVO.getDireccion());
        customer.setLocalidad(customerVO.getLocalidad());
        customer.setProvincia(customerVO.getProvincia());

        return customer;
    }
}
