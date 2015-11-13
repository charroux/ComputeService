package messageBroker;

import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class MainAsynchronousTopicSubscriber {

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(messageBroker.MainAsynchronousTopicSubscriber.class);
		
		try{
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			TopicConnection connection = factory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = (Topic) applicationContext.getBean("topic");
			TopicSubscriber subscriber = session.createSubscriber(topic) ;
			
			MongoOperations mongoOperation = (MongoOperations)applicationContext.getBean("mongoTemplate");
			
			subscriber.setMessageListener( new RentMessageListener(mongoOperation) ) ;
			connection.start();
			
			logger.log(Level.INFO, "Topic subscriber is ready");
			
		}catch(Exception e){
			logger.log(Level.ERROR, e.getLocalizedMessage(), e);
		}
		


	}

}
