package messageBroker;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import model.Car;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dto.CarDTO;

public class RentMessageQueueSender{

	QueueConnection connection;
	QueueSender sender;
	QueueSession session;
	
	public RentMessageQueueSender() throws Exception{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
		QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
		connection = factory.createQueueConnection() ;
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) applicationContext.getBean("queue");
		sender = session.createSender(queue) ;
	}
	 
	public  void send(Car car) throws Exception{
		ObjectMessage message = session.createObjectMessage(new CarDTO(car));		
		sender.send(message);

	}
	
	protected void finalize() throws Throwable{
		session.close();
		connection.stop();
		connection.close();
	}

}
