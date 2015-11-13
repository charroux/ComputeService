package messageBroker;

import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import model.Car;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dto.CarDTO;

public class RentMessageTopicPublisher {

	TopicConnection connection;
	TopicSession session;
	TopicPublisher publisher;
	
	public RentMessageTopicPublisher() throws Exception{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
		TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
		connection = factory.createTopicConnection();
		session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) applicationContext.getBean("topic");
		publisher = session.createPublisher(topic) ;
	}
	
	public void publish(Car car) throws Exception{
		ObjectMessage message = session.createObjectMessage(new CarDTO(car));
		publisher.publish(message);
		session.close();
		connection.stop();
		connection.close();
	}
	
	protected void finalize() throws Throwable{
		session.close();
		connection.stop();
		connection.close();
	}

}
