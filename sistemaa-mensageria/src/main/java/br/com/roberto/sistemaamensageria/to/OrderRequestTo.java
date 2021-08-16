package br.com.roberto.sistemaamensageria.to;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@correlationId", scope = OrderRequestTo.class)
public class OrderRequestTo {
	private String correlationId;
	private Long idServico;
	private String servico;
	private List<TarifaTo> tarifasTo = new ArrayList<>();

	public String toString() {
		JSONObject jsonInfo = new JSONObject();

		try {
			jsonInfo.put("correlationId", this.correlationId);
			jsonInfo.put("idServico", this.idServico);
			jsonInfo.put("servico", this.servico);

			JSONArray productArray = new JSONArray();
			if (this.tarifasTo != null) {

				this.tarifasTo.forEach(tarifaTo -> {

					JSONObject subJson = new JSONObject();

					try {
						subJson.put("id", tarifaTo.getId());
						subJson.put("nome", tarifaTo.getNome());
					} catch (JSONException e) {

					}

					productArray.put(subJson);
				});
			}
			jsonInfo.put("tarifasTo", productArray);
		} catch (JSONException e1) {
		}

		return jsonInfo.toString();
	}

}
